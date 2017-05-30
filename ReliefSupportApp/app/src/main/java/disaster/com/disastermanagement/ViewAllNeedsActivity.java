package disaster.com.disastermanagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
 * Created by Sumudu on 5/29/2017.
 */

public class ViewAllNeedsActivity extends Activity {
    NetworkStatChecker n = new NetworkStatChecker();
    DBOperations dbOperations = new DBOperations();
    Data data = new Data();

    Context context;
    TextView hiddenLayout;
    ListView viewAllRequirementsListView;
    SwipeRefreshLayout swiper;

    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_requirements_layout);
        context = ViewAllNeedsActivity.this;

        swiper = (SwipeRefreshLayout) findViewById(R.id.swiper);
        swiper.setColorSchemeColors(data.getSWIPER_COLOR_LIST());
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiper.setRefreshing(true);
                new GetAllNeedDetails().execute();
            }
        });

        hiddenLayout = (TextView) findViewById(R.id.hiddenLayout);
        viewAllRequirementsListView = (ListView) findViewById(R.id.viewAllRequirementsListView);
        hiddenLayout = (TextView) findViewById(R.id.hiddenLayout);

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOption();
            }
        });

        new GetAllNeedDetails().execute();
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

    class GetAllNeedDetails extends AsyncTask<String, Void, String[][]> {
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
                String[][] res = dbOperations.getAllNeeds();
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
                    String[] needIDList = result[0];
                    String[] needNameList = result[1];
                    String[] needTelephoneList = result[2];
                    String[] needCityList = result[3];
                    String[] needTextList = result[4];
                    String[] needHeadCountListList = result[5];
                    String[] needCreatedAtList = result[6];
                    String[] needUpdatedAtList = result[7];
                    String[] needIdentifierList = result[8];
                    String[] needAddressList = result[9];

                    CustomAllRequirementsAdapter customAllRequirementsAdapter
                            = new CustomAllRequirementsAdapter(
                            context,
                            ViewAllNeedsActivity.this,

                            needIDList,
                            needNameList,
                            needTelephoneList,
                            needAddressList,
                            needCityList,
                            needTextList,
                            needHeadCountListList,
                            needCreatedAtList,
                            needUpdatedAtList,
                            needIdentifierList);

                    customAllRequirementsAdapter.notifyDataSetChanged();
                    viewAllRequirementsListView.setAdapter(customAllRequirementsAdapter);

                    hiddenLayout.setVisibility(View.GONE);
                    viewAllRequirementsListView.setVisibility(View.VISIBLE);
                }
                catch (Exception e){
                    if(this.internetAvailable){
                        hiddenLayout.setVisibility(View.VISIBLE);
                        viewAllRequirementsListView.setVisibility(View.GONE);
                        Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        hiddenLayout.setVisibility(View.VISIBLE);
                        viewAllRequirementsListView.setVisibility(View.GONE);
                        Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            else if(this.internetAvailable){
                hiddenLayout.setVisibility(View.VISIBLE);
                viewAllRequirementsListView.setVisibility(View.GONE);
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
            }

            else{
                hiddenLayout.setVisibility(View.VISIBLE);
                viewAllRequirementsListView.setVisibility(View.GONE);
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
