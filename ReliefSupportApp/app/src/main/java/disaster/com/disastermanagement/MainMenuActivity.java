package disaster.com.disastermanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by WAKENSYS on 5/29/2017.
 */

public class MainMenuActivity extends Activity {
    DBOperations dbOperations = new DBOperations();
    NetworkStatChecker n = new NetworkStatChecker();

    Context context;

    ImageView
            addNewHelperButton,
            addNewRequirementButton,
            viewAllRequirementsButton,
            viewAllHelperButton,
            viewNeedCountChartButton,
            viewHelpCountChartButton;

    ListView navigationListView;

    DrawerLayout drawerLayout; // drawer_layout
    NavigationView navigationView; // whatYouWantInLeftDrawer

    ImageView nav;
    Data data = new Data();

    String lan = "";
    TextView welcomeTextDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        context = MainMenuActivity.this;

        welcomeTextDisplay = (TextView) findViewById(R.id.welcomeTextDisplay);

        SharedPreferences sp = getSharedPreferences("language", 0);
        lan += sp.getString("lan", "1");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationListView = (ListView) findViewById(R.id.navigationListView);

        switch (lan){
            case "1" :
                CustomNavigationListAdapter customNavigationListAdapter1 = new CustomNavigationListAdapter(context, MainMenuActivity.this, data.getNAVIGATION_NAME_LIST_ENGLISH(), data.getNAVIGATION_ICON_LIST_ENGLISH());
                customNavigationListAdapter1.notifyDataSetChanged();
                navigationListView.setAdapter(customNavigationListAdapter1);
                break;

            case "2" :
                CustomNavigationListAdapter customNavigationListAdapter2 = new CustomNavigationListAdapter(context, MainMenuActivity.this, data.getNAVIGATION_NAME_LIST_SINHALA(), data.getNAVIGATION_ICON_LIST_SINHALA());
                customNavigationListAdapter2.notifyDataSetChanged();
                navigationListView.setAdapter(customNavigationListAdapter2);
                break;

            case "3" :
                CustomNavigationListAdapter customNavigationListAdapter3 = new CustomNavigationListAdapter(context, MainMenuActivity.this, data.getNAVIGATION_NAME_LIST_TAMIL(), data.getNAVIGATION_ICON_LIST_TAMIL());
                customNavigationListAdapter3.notifyDataSetChanged();
                navigationListView.setAdapter(customNavigationListAdapter3);
                break;

        }

        nav = (ImageView) findViewById(R.id.nav);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        addNewHelperButton = (ImageView) findViewById(R.id.addNewHelperButton);
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

                final TextView usernameTextDisplay = (TextView) alertDialog.findViewById(R.id.userNameTextDisplay);
                final TextView helpingMethodTextDisplay = (TextView) alertDialog.findViewById(R.id.helpingMethodTextDisplay);
                final TextView infoTextDisplay = (TextView) alertDialog.findViewById(R.id.infoTextDisplay);
                final TextView addressTextDisplay = (TextView) alertDialog.findViewById(R.id.addressTextDisplay);
                final TextView cityTextDisplay = (TextView) alertDialog.findViewById(R.id.cityTextDisplay);
                final TextView telNoTextDisplay = (TextView) alertDialog.findViewById(R.id.telNoTextDisplay);
                final TextView submitButtonText = (TextView) alertDialog.findViewById(R.id.submitButtonText);

                switch(lan){
                    case "1" : //EN
                        usernameTextDisplay.setText("Your Name");
                        userNameField.setHint("Enter your name here");

                        helpingMethodTextDisplay.setText("Donation");
                        helpingMethodField.setHint("Enter your donation here");

                        infoTextDisplay.setText("Donation info");
                        infoField.setHint("Enter your donation info here");

                        addressTextDisplay.setText("Address");
                        addressField.setHint("Enter your address here");

                        cityTextDisplay.setText("City");
                        cityField.setHint("Enter your city here");

                        telNoTextDisplay.setText("Contact Number");
                        telNoField.setHint("Enter your contact number here");

                        submitButtonText.setText("SUBMIT");
                        break;

                    case "2" : //SI
                        usernameTextDisplay.setText("ඔබගේ නම");
                        userNameField.setHint("ඔබගේ නම මෙහි සඳහන් කරන්න");

                        helpingMethodTextDisplay.setText("ආධාරය");
                        helpingMethodField.setHint("ඔබගේ ආධාරය මෙහි සඳහන් කරන්න");

                        infoTextDisplay.setText("ආධාරය ගැන විස්තර");
                        infoField.setHint("ඔබගේ ආධාරය ගැන විස්තර මෙහි සඳහන් කරන්න");

                        addressTextDisplay.setText("ලිපිනය");
                        addressField.setHint("ඔබගේ ලිපිනය මෙහි සඳහන් කරන්න");

                        cityTextDisplay.setText("නගරය");
                        cityField.setHint("ඔබගේ නගරය මෙහි සඳහන් කරන්න");

                        telNoTextDisplay.setText("දුරකථන අංකය");
                        telNoField.setHint("ඔබගේ දුරකථන අංකය මෙහි සඳහන් කරන්න");

                        submitButtonText.setText("ඇතුළු කරන්න");
                        break;

                    case "3" : //TA
                        usernameTextDisplay.setText("உங்கள் பெயர்");
                        userNameField.setHint("உனது பெயரை இங்கே பதியவும்");

                        helpingMethodTextDisplay.setText("நன்கொடையை");
                        helpingMethodField.setHint("உனது நன்கொடையை இங்கே பதியவும்");

                        infoTextDisplay.setText("நன்கொடை விபரம்");
                        infoField.setHint("உனது ந்னகொடை விபரத்தினை இங்கே பதியவும்");

                        addressTextDisplay.setText("முகவரியை");
                        addressField.setHint("உனது முகவரியை இங்கே பதியவும்");

                        cityTextDisplay.setText("நகரத்தினை");
                        cityField.setHint("உனது நகரத்தினை இங்கே பதியவும்");

                        telNoTextDisplay.setText("தொலைபேசி எண்");
                        telNoField.setHint("உனது தொலைபேசி இலக்கதிமை இங்கே பதியவும்");

                        submitButtonText.setText("சமர்பித்தல்");
                        break;
                }

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
                            switch (lan){
                                case "1" :
                                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                                    break;

                                case "2" :
                                    Toast.makeText(context, "කරුණාකර සියල්ල පුරවන්න", Toast.LENGTH_SHORT).show();
                                    break;

                                case "3" :
                                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        }

                        else{
                            new AddNewHelperRecord(name, telephone,address, city, donation, info).execute();
                        }
                    }
                });
                alertDialog.show();
            }
        });

        addNewRequirementButton = (ImageView) findViewById(R.id.addNewRequirementButton);
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

                final TextView usernameTextDisplay = (TextView) alertDialog.findViewById(R.id.userNameTextDisplay);
                final TextView requirementTextDisplay = (TextView) alertDialog.findViewById(R.id.requirementTextDisplay);
                final TextView numOfPeopleTextDisplay = (TextView) alertDialog.findViewById(R.id.numOfPeopleTextDisplay);
                final TextView addressTextDisplay = (TextView) alertDialog.findViewById(R.id.addressTextDisplay);
                final TextView cityTextDisplay = (TextView) alertDialog.findViewById(R.id.cityTextDisplay);
                final TextView telNoTextDisplay = (TextView) alertDialog.findViewById(R.id.telNoTextDisplay);
                final TextView submitButtonText = (TextView) alertDialog.findViewById(R.id.submitButtonText);

                switch(lan){
                    case "1" : //EN
                        usernameTextDisplay.setText("Your Name");
                        userNameField.setHint("Enter your name here");

                        requirementTextDisplay.setText("Requirement");
                        requirementField.setHint("Enter your Requirement here");

                        numOfPeopleTextDisplay.setText("No. of people");
                        numOfPeopleField.setHint("Enter No. of people here");

                        addressTextDisplay.setText("Address");
                        addressField.setHint("Enter your address here");

                        cityTextDisplay.setText("City");
                        cityField.setHint("Enter your city here");

                        telNoTextDisplay.setText("Contact Number");
                        telNoField.setHint("Enter your contact number here");

                        submitButtonText.setText("SUBMIT");
                        break;

                    case "2" : //SI
                        usernameTextDisplay.setText("ඔබගේ නම");
                        userNameField.setHint("ඔබගේ නම මෙහි සඳහන් කරන්න");

                        requirementTextDisplay.setText("අවශ්යතාවය");
                        requirementField.setHint("ඔබගේ අවශ්යතාවය මෙහි සඳහන් කරන්න");

                        numOfPeopleTextDisplay.setText("පුද්ගලයින් ගණන");
                        numOfPeopleField.setHint("පුද්ගලයින් ගණන මෙහි සඳහන් කරන්න");

                        addressTextDisplay.setText("ලිපිනය");
                        addressField.setHint("ඔබගේ ලිපිනය මෙහි සඳහන් කරන්න");

                        cityTextDisplay.setText("නගරය");
                        cityField.setHint("ඔබගේ නගරය මෙහි සඳහන් කරන්න");

                        telNoTextDisplay.setText("දුරකථන අංකය");
                        telNoField.setHint("ඔබගේ දුරකථන අංකය මෙහි සඳහන් කරන්න");

                        submitButtonText.setText("ඇතුළු කරන්න");
                        break;

                    case "3" : //TA
                        usernameTextDisplay.setText("உங்கள் பெயர்");
                        userNameField.setHint("உனது பெயரை இங்கே பதியவும்");

                        requirementTextDisplay.setText("தேவை");
                        requirementField.setHint("உங்களது தேவையை இங்கே பதியவும்");

                        numOfPeopleTextDisplay.setText("எத்தனை பேர்கள்");
                        numOfPeopleField.setHint("எத்தனை பேர் என்பததை இங்கே பதியவும்");

                        addressTextDisplay.setText("முகவரி");
                        addressField.setHint("உனது முகவரியை இங்கே பதியவும்");

                        cityTextDisplay.setText("நகரம்");
                        cityField.setHint("உனது நகரத்தினை இங்கே பதியவும்");

                        telNoTextDisplay.setText("தொலைபேசி எண்");
                        telNoField.setHint("உனது தொலைபேசி இலக்கதிமை இங்கே பதியவும்");

                        submitButtonText.setText("சமர்பித்தல்");
                        break;
                }

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
                            switch (lan){
                                case "1" :
                                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                                    break;

                                case "2" :
                                    Toast.makeText(context, "කරුණාකර සියල්ල පුරවන්න", Toast.LENGTH_SHORT).show();
                                    break;

                                case "3" :
                                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }

                        else{
                            new AddNewRequirementRecord(name, telephone,address, city, requirement, count).execute();
                        }
                    }
                });

                alertDialog.show();
            }
        });

        viewAllRequirementsButton = (ImageView) findViewById(R.id.viewAllRequirementsButton);
        viewAllRequirementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewAllNeedsActivity.class);
                startActivity(i);
                finish();
            }
        });

        viewAllHelperButton = (ImageView) findViewById(R.id.viewAllHelperButton);
        viewAllHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewAllHelpersActivity.class);
                startActivity(i);
                finish();
            }
        });

        viewNeedCountChartButton = (ImageView) findViewById(R.id.viewNeedCountChartButton);
        viewNeedCountChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewNeedCountChartActivity.class);
                startActivity(i);
                finish();
            }
        });

        viewHelpCountChartButton = (ImageView) findViewById(R.id.viewHelpCountChartButton);
        viewHelpCountChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewHelpersCountChartActivity.class);
                startActivity(i);
                finish();
            }
        });

        switch (lan){
            case "1" :
                welcomeTextDisplay.setText("Welcome");
                Picasso
                        .with(context)
                        .load(R.drawable.ic_en_1)
                        .fit()
                        .into(addNewHelperButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_en_2)
                        .fit()
                        .into(addNewRequirementButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_en_3)
                        .fit()
                        .into(viewAllHelperButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_en_4)
                        .fit()
                        .into(viewAllRequirementsButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_en_5)
                        .fit()
                        .into(viewHelpCountChartButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_en_6)
                        .fit()
                        .into(viewNeedCountChartButton);
                break; // EN

            case "2" :
                welcomeTextDisplay.setText("ආයුබෝවන්");

                Picasso
                        .with(context)
                        .load(R.drawable.ic_tx_1)
                        .fit()
                        .into(addNewHelperButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_tx_2)
                        .fit()
                        .into(addNewRequirementButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_tx_3)
                        .fit()
                        .into(viewAllHelperButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_tx_4)
                        .fit()
                        .into(viewAllRequirementsButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_tx_5)
                        .fit()
                        .into(viewHelpCountChartButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_tx_6)
                        .fit()
                        .into(viewNeedCountChartButton);
                break; // SI

            case "3" :
                welcomeTextDisplay.setText("வரவேற்கிறோம்");

                Picasso
                        .with(context)
                        .load(R.drawable.ic_ta_1)
                        .fit()
                        .into(addNewHelperButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_ta_2)
                        .fit()
                        .into(addNewRequirementButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_ta_3)
                        .fit()
                        .into(viewAllHelperButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_ta_4)
                        .fit()
                        .into(viewAllRequirementsButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_ta_5)
                        .fit()
                        .into(viewHelpCountChartButton);

                Picasso
                        .with(context)
                        .load(R.drawable.ic_ta_6)
                        .fit()
                        .into(viewNeedCountChartButton);
                break; // TA
        }
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        switch (lan){
            case "1" :
                new AlertDialog.Builder(this)
                        .setTitle("Exit")
                        .setMessage("Do you really want to exit?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                System.exit(0);
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                break;

            case "2" :
                new AlertDialog.Builder(this)
                        .setTitle("ඉවත්වීම")
                        .setMessage("ඉවත්වීමට අවශ්\u200Dයයි ද?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                System.exit(0);
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                break;

            case "3" :
                new AlertDialog.Builder(this)
                        .setTitle("வெளியேறுதல்")
                        .setMessage("நீங்கள் வெளியேற விரும்புகிறீர்களா?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                System.exit(0);
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                break;
        }
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
            switch (lan){
                case "1" :
                    progressDialog = ProgressDialog.show(context, "Submitting", "Please wait");
                    break;

                case "2" :
                    progressDialog = ProgressDialog.show(context, "සඳහන් කරමින් පවතී", "මඳක් ඉවසන්න");
                    break;

                case "3" :
                    progressDialog = ProgressDialog.show(context, "சமர்பிக்கப்படுகின்றது", "தயவு செய்து காத்திருங்கள்");
                    break;
            }
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
                switch (lan){
                    case "1" :
                        Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
                        break;

                    case "2" :
                        Toast.makeText(context, "සාර්ථකයි", Toast.LENGTH_SHORT).show();
                        break;

                    case "3" :
                        Toast.makeText(context, "வெற்றிகரமாக பதியப்பட்டது", Toast.LENGTH_SHORT).show();
                        break;
                }
                try{
                    progressDialog.dismiss();
                }
                catch(Exception ignored){}
            }

            else if(this.internetAvailable){
                switch (lan){
                    case "1" :
                        Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
                        break;

                    case "2" :
                        Toast.makeText(context, "දත්ත නොමැත", Toast.LENGTH_SHORT).show();
                        break;

                    case "3" :
                        Toast.makeText(context, "தகவல் எதனையும் பெறமுடியவில்லை", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            else{
                switch (lan){
                    case "1" :
                        Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                        break;

                    case "2" :
                        Toast.makeText(context, "අන්තර්ජාල සම්බන්ධතාවය පරික්ෂා කරන්න", Toast.LENGTH_SHORT).show();
                        break;

                    case "3" :
                        Toast.makeText(context, "இணைய இணைப்பை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                        break;
                }
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
            switch (lan){
                case "1" :
                    progressDialog = ProgressDialog.show(context, "Submitting", "Please wait");
                    break;

                case "2" :
                    progressDialog = ProgressDialog.show(context, "සඳහන් කරමින් පවතී", "මඳක් ඉවසන්න");
                    break;

                case "3" :
                    progressDialog = ProgressDialog.show(context, "சமர்பிக்கப்படுகின்றது", "தயவு செய்து காத்திருங்கள்");
                    break;
            }
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
                switch (lan){
                    case "1" :
                        Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
                        break;

                    case "2" :
                        Toast.makeText(context, "සාර්ථකයි", Toast.LENGTH_SHORT).show();
                        break;

                    case "3" :
                        Toast.makeText(context, "வெற்றிகரமாக பதியப்பட்டது", Toast.LENGTH_SHORT).show();
                        break;
                }
                try{
                    progressDialog.dismiss();
                }
                catch(Exception ignored){}
            }

            else if(this.internetAvailable){

                switch (lan){
                    case "1" :
                        Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
                        break;

                    case "2" :
                        Toast.makeText(context, "දත්ත නොමැත", Toast.LENGTH_SHORT).show();
                        break;

                    case "3" :
                        Toast.makeText(context, "தகவல் எதனையும் பெறமுடியவில்லை", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            else{
                switch (lan){
                    case "1" :
                        Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                        break;

                    case "2" :
                        Toast.makeText(context, "අන්තර්ජාල සම්බන්ධතාවය පරික්ෂා කරන්න", Toast.LENGTH_SHORT).show();
                        break;

                    case "3" :
                        Toast.makeText(context, "இணைய இணைப்பை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }
}
