package disaster.com.disastermanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by WAKENSYS on 5/29/2017.
 */

public class MainMenuActivity extends Activity {
    DBOperations dbOperations = new DBOperations();
    NetworkStatChecker n = new NetworkStatChecker();

    Context context;
    CardView addNewHelperButton, addNewRequirementButton, viewAllRequirementsButton, viewAllHelperButton;

    ListView navigationListView;

    DrawerLayout drawerLayout; // drawer_layout
    NavigationView navigationView; // whatYouWantInLeftDrawer

    ImageView nav;
    Data data = new Data();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        context = MainMenuActivity.this;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationListView = (ListView) findViewById(R.id.navigationListView);

        CustomNavigationListAdapter customNavigationListAdapter = new CustomNavigationListAdapter(context, MainMenuActivity.this, data.getNAVIGATION_NAME_LIST(), data.getNAVIGATION_ICON_LIST());
        customNavigationListAdapter.notifyDataSetChanged();
        navigationListView.setAdapter(customNavigationListAdapter);

        nav = (ImageView) findViewById(R.id.nav);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        addNewHelperButton = (CardView) findViewById(R.id.addNewHelperButton);
        addNewHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.add_new_helper_method_layout);
                alertDialog.setCancelable(true);

                Window window = alertDialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                final EditText userNameField = (EditText) alertDialog.findViewById(R.id.userNameField);
                final EditText helpingMethodField = (EditText) alertDialog.findViewById(R.id.helpingMethodField);
                final EditText infoField = (EditText) alertDialog.findViewById(R.id.infoField);
                final EditText addressField = (EditText) alertDialog.findViewById(R.id.addressField);
                final EditText cityField = (EditText) alertDialog.findViewById(R.id.cityField);
                final EditText telNoField = (EditText) alertDialog.findViewById(R.id.telNoField);

                CardView submitButton = (CardView) alertDialog.findViewById(R.id.submitButton);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = userNameField.getText().toString().trim();
                        String telephone = telNoField.getText().toString().trim();
                        String address = addressField.getText().toString().trim();
                        String city = cityField.getText().toString().trim();
                        String donation = helpingMethodField.getText().toString().trim();
                        String info = infoField.getText().toString().trim();

                        if(name.equalsIgnoreCase("") || telephone.equalsIgnoreCase("") || address.equalsIgnoreCase("")
                                || city.equalsIgnoreCase("") || donation.equalsIgnoreCase("") || info.equalsIgnoreCase("")){
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            new AddNewHelperRecord(name, telephone,address, city, donation, info).execute();
                        }
                    }
                });


                alertDialog.show();
            }
        });

        addNewRequirementButton = (CardView) findViewById(R.id.addNewRequirementButton);
        addNewRequirementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.add_new_requirement_layout);
                alertDialog.setCancelable(true);

                Window window = alertDialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                final EditText userNameField = (EditText) alertDialog.findViewById(R.id.userNameField);
                final EditText requirementField = (EditText) alertDialog.findViewById(R.id.requirementField);
                final EditText numOfPeopleField = (EditText) alertDialog.findViewById(R.id.numOfPeopleField);
                final EditText addressField = (EditText) alertDialog.findViewById(R.id.addressField);
                final EditText cityField = (EditText) alertDialog.findViewById(R.id.cityField);
                final EditText telNoField = (EditText) alertDialog.findViewById(R.id.telNoField);

                CardView submitButton = (CardView) alertDialog.findViewById(R.id.submitButton);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = userNameField.getText().toString().trim();
                        String telephone = telNoField.getText().toString().trim();
                        String address = addressField.getText().toString().trim();
                        String city = cityField.getText().toString().trim();
                        String requirement = requirementField.getText().toString().trim();
                        String count = numOfPeopleField.getText().toString().trim();

                        if(name.equalsIgnoreCase("") || telephone.equalsIgnoreCase("") || address.equalsIgnoreCase("")
                                || city.equalsIgnoreCase("") || requirement.equalsIgnoreCase("") || count.equalsIgnoreCase("")){
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            new AddNewRequirementRecord(name, telephone,address, city, requirement, count).execute();
                        }
                    }
                });

                alertDialog.show();
            }
        });

        viewAllRequirementsButton = (CardView) findViewById(R.id.viewAllRequirementsButton);
        viewAllRequirementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewAllNeedsActivity.class);
                startActivity(i);
                finish();
            }
        });

        viewAllHelperButton = (CardView) findViewById(R.id.viewAllHelperButton);
        viewAllHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewAllHelpersActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you really want to exit?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        System.exit(0);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }


    class AddNewHelperRecord extends AsyncTask<String, Void, Boolean> {
        boolean internetAvailable = false;
        ProgressDialog progressDialog;

        String name, telephone, address, city, donation, information;

        public AddNewHelperRecord(String name, String telephone, String address, String city, String donation, String information){
            this.name = name;
            this.telephone = telephone;
            this.address = address;
            this.city = city;
            this.donation = donation;
            this.information = information;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "Submitting", "Please wait");
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                boolean res = dbOperations.addNewDonation(name, telephone, address, city, donation, information);
                return res;
            }
            else{
                this.internetAvailable = false;
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
                try{
                    progressDialog.dismiss();
                }
                catch(Exception ignored){}
            }

            else if(this.internetAvailable){
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class AddNewRequirementRecord extends AsyncTask<String, Void, Boolean> {
        boolean internetAvailable = false;
        ProgressDialog progressDialog;

        String name, telephone, address, city, need, headCount;

        public AddNewRequirementRecord(String name, String telephone, String address, String city, String need, String headCount){
            this.name = name;
            this.telephone = telephone;
            this.address = address;
            this.city = city;
            this.need = need;
            this.headCount = headCount;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "Submitting", "Please wait");
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                boolean res = dbOperations.addNewNeed(name, telephone, address, city, need, headCount);
                return res;
            }
            else{
                this.internetAvailable = false;
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
                try{
                    progressDialog.dismiss();
                }
                catch(Exception ignored){}
            }

            else if(this.internetAvailable){
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
