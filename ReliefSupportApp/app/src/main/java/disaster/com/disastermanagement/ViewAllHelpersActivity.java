package disaster.com.disastermanagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by WAKENSYS on 5/29/2017.
 */

public class ViewAllHelpersActivity extends Activity {
    Context context;
    ListView viewAllHelpersListView;

    TextView hiddenLayout;

    NetworkStatChecker n = new NetworkStatChecker();
    DBOperations dbOperations = new DBOperations();
    Data data = new Data();

    SwipeRefreshLayout swiper;
    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_helpers_layout);
        context = ViewAllHelpersActivity.this;

        swiper = (SwipeRefreshLayout) findViewById(R.id.swiper);
        swiper.setColorSchemeColors(data.getSWIPER_COLOR_LIST());
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiper.setRefreshing(true);
                new GetAllHelpDetails().execute();
            }
        });

        viewAllHelpersListView = (ListView) findViewById(R.id.viewAllHelpersListView);
        hiddenLayout = (TextView) findViewById(R.id.hiddenLayout);

        new GetAllHelpDetails().execute();

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOption();
            }
        });
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        Intent i = new Intent(context, MainMenuActivity.class);
        startActivity(i);
        finish();
    }

    class GetAllHelpDetails extends AsyncTask<String, Void, String[][]> {
        boolean internetAvailable = false;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "Loading", "Please wait");
        }

        @Override
        protected String[][] doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                String[][] res = dbOperations.getAllDonations();
                return res;
            }
            else{
                this.internetAvailable = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[][] result) {
            if(result != null){
               try{
                   String[] donationIDList = result[0];
                   String[] donationNameList = result[1];
                   String[] donationTelephoneList = result[2];
                   String[] donationCityList = result[3];
                   String[] donationTextList = result[4];
                   String[] donationInformationList = result[5];
                   String[] donationCreatedAtList = result[6];
                   String[] donationUpdatedAtList = result[7];
                   String[] donationIdentifierList = result[8];

                   String[] donationAddressList = result[9];

                   CustomAllHelpersAdapter customAllHelpersAdapter
                           = new CustomAllHelpersAdapter(
                                   context,
                           ViewAllHelpersActivity.this,

                           donationIDList,
                           donationNameList,
                           donationTelephoneList,
                           donationAddressList,
                           donationCityList,
                           donationTextList,
                           donationInformationList,
                           donationCreatedAtList,
                           donationUpdatedAtList,
                           donationIdentifierList);

                   customAllHelpersAdapter.notifyDataSetChanged();
                   viewAllHelpersListView.setAdapter(customAllHelpersAdapter);

                   hiddenLayout.setVisibility(View.GONE);
                   viewAllHelpersListView.setVisibility(View.VISIBLE);
               }
               catch (Exception e){
                    if(this.internetAvailable){
                        hiddenLayout.setVisibility(View.VISIBLE);
                        viewAllHelpersListView.setVisibility(View.GONE);
                       Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
                   }

                   else{
                        hiddenLayout.setVisibility(View.VISIBLE);
                        viewAllHelpersListView.setVisibility(View.GONE);
                       Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                   }
               }
            }

            else if(this.internetAvailable){
                hiddenLayout.setVisibility(View.VISIBLE);
                viewAllHelpersListView.setVisibility(View.GONE);
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
            }

            else{
                hiddenLayout.setVisibility(View.VISIBLE);
                viewAllHelpersListView.setVisibility(View.GONE);
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }

            try{
                progressDialog.dismiss();
                swiper.setRefreshing(false);
            }
            catch(Exception ignored){}
        }
    }
}
