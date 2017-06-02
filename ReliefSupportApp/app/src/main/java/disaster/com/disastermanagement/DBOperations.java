package disaster.com.disastermanagement;

import android.net.Uri;
import android.os.StrictMode;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WAKENSYS on 4/24/2017.
 */

public class DBOperations {
    Data data = new Data();

    public String[][] getHelpersCountDetails(){
        String
                line,
                result,

                methodNameListAsString = "",
                needCountListAsString = "";

        InputStream is;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "getHelpersChartDetails.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                try {
                    conn.connect();
                    is = conn.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        result = Html.fromHtml(result).toString();
                        is.close();

                        try {
                            JSONArray jArray = new JSONArray(result);
                            int count = jArray.length();
                            for (int i = 0; i < count; i++) {
                                JSONObject jObject = jArray.getJSONObject(i);

                                if(jObject.getString("identifier").equalsIgnoreCase("a")){
                                    methodNameListAsString += "Android#";
                                }
                                else if(jObject.getString("identifier").equalsIgnoreCase("b")){
                                    methodNameListAsString += "Bot#";
                                }

                                else if(jObject.getString("identifier").equalsIgnoreCase("i")){
                                    methodNameListAsString += "IOS#";
                                }

                                else if(jObject.getString("identifier").equalsIgnoreCase("w")){
                                    methodNameListAsString += "Web#";
                                }

                                needCountListAsString  += jObject.getString("countage") + "#";

                            }
                            return new String[][]{
                                    methodNameListAsString.split("#"),
                                    needCountListAsString.split("#")};
                        } catch (Exception ex) {
                            System.out.println("Ex " + ex.toString());
                            return null;
                        }
                    } catch (Exception ex) {
                        System.out.println("Ex " + ex.toString());
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return null;
        }
    }

    public String[][] getEmergencyNumberList(){
        String
                line,
                result,

                nameListAsString = "",
                numberListAsString = "";

        InputStream is;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "getEmergencyNumberList.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                try {
                    conn.connect();
                    is = conn.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        result = Html.fromHtml(result).toString();
                        is.close();

                        try {
                            JSONArray jArray = new JSONArray(result);
                            int count = jArray.length();
                            for (int i = 0; i < count; i++) {
                                JSONObject jObject = jArray.getJSONObject(i);

                                nameListAsString  += jObject.getString("emergency_number_name") + "#";
                                numberListAsString  += jObject.getString("emergency_number_number") + "#";

                            }
                            return new String[][]{
                                    nameListAsString.split("#"),
                                    numberListAsString.split("#")};
                        } catch (Exception ex) {
                            System.out.println("Ex " + ex.toString());
                            return null;
                        }
                    } catch (Exception ex) {
                        System.out.println("Ex " + ex.toString());
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return null;
        }
    }

    public String[][] getNeedCountDetails(){
        String
                line,
                result,

                methodNameListAsString = "",
                needCountListAsString = "";

        InputStream is;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "getNeedsChartDetails.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                try {
                    conn.connect();
                    is = conn.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        result = Html.fromHtml(result).toString();
                        is.close();

                        try {
                            JSONArray jArray = new JSONArray(result);
                            int count = jArray.length();
                            for (int i = 0; i < count; i++) {
                                JSONObject jObject = jArray.getJSONObject(i);

                                if(jObject.getString("identifier").equalsIgnoreCase("a")){
                                    methodNameListAsString += "Android#";
                                }
                                else if(jObject.getString("identifier").equalsIgnoreCase("b")){
                                    methodNameListAsString += "Bot#";
                                }

                                else if(jObject.getString("identifier").equalsIgnoreCase("i")){
                                    methodNameListAsString += "IOS#";
                                }

                                else if(jObject.getString("identifier").equalsIgnoreCase("w")){
                                    methodNameListAsString += "Web#";
                                }

                                needCountListAsString  += jObject.getString("countage") + "#";

                            }
                            return new String[][]{
                                    methodNameListAsString.split("#"),
                                    needCountListAsString.split("#")};
                        } catch (Exception ex) {
                            System.out.println("Ex " + ex.toString());
                            return null;
                        }
                    } catch (Exception ex) {
                        System.out.println("Ex " + ex.toString());
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return null;
        }
    }

    public String[][] getAllNotifications(){
        String
                line,
                result,

                mainValueListAsString = "",
                subValue1ListAsString = "",
                subValue2ListAsString = "",
                subValue3ListAsString = "",
                subValue4ListAsString = "",
                dateTimeListAsString = "";

        InputStream is;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "getAllNotifications.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                try {
                    conn.connect();
                    is = conn.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        result = Html.fromHtml(result).toString();
                        is.close();

                        try {
                            JSONArray jArray = new JSONArray(result);
                            int count = jArray.length();
                            for (int i = 0; i < count; i++) {
                                JSONObject jObject = jArray.getJSONObject(i);

                                mainValueListAsString += jObject.getString("notification_main_value") + "#";

                                if(jObject.getString("notification_sub_value_1").equalsIgnoreCase("") || jObject.getString("notification_sub_value_1").isEmpty()){
                                    subValue1ListAsString += "-#";
                                }
                                else{
                                    subValue1ListAsString += jObject.getString("notification_sub_value_1") + "#";
                                }

                                if(jObject.getString("notification_sub_value_2").equalsIgnoreCase("") || jObject.getString("notification_sub_value_2").isEmpty()){
                                    subValue2ListAsString += "-#";
                                }
                                else{
                                    subValue2ListAsString  += jObject.getString("notification_sub_value_2") + "#";
                                }

                                if(jObject.getString("notification_sub_value_3").equalsIgnoreCase("") || jObject.getString("notification_sub_value_3").isEmpty()){
                                    subValue3ListAsString += "-#";
                                }
                                else{
                                    subValue3ListAsString += jObject.getString("notification_sub_value_3") + "#";
                                }

                                if(jObject.getString("notification_sub_value_4").equalsIgnoreCase("") || jObject.getString("notification_sub_value_4").isEmpty()){
                                    subValue4ListAsString += "-#";
                                }
                                else{
                                    subValue4ListAsString += jObject.getString("notification_sub_value_4") + "#";
                                }

                                dateTimeListAsString += jObject.getString("notification_date_time") + "#";
                            }
                            return new String[][]{
                                    mainValueListAsString.split("#"),

                                    subValue1ListAsString.split("#"),
                                    subValue2ListAsString.split("#"),
                                    subValue3ListAsString.split("#"),
                                    subValue4ListAsString.split("#"),
                                    dateTimeListAsString.split("#")};
                        } catch (Exception ex) {
                            System.out.println("Ex " + ex.toString());
                            return null;
                        }
                    } catch (Exception ex) {
                        System.out.println("Ex " + ex.toString());
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return null;
        }
    }

    public String[][] getAllNeeds(){
        String
                line,
                result,

                needIDListAsString = "",
                needNameListAsString = "",
                needTelephoneListAsString = "",
                needCityListAsString = "",
                needTextListAsString = "",
                needHeadCountListAsString = "",
                needCreatedAtListAsString = "",
                needUpdatedAtListAsString = "",
                needIdentifierListAsString = "",
                needAddressListAsString = "";

        InputStream is;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "getAllNeeds.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                try {
                    conn.connect();
                    is = conn.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        result = Html.fromHtml(result).toString();
                        is.close();

                        try {
                            JSONArray jArray = new JSONArray(result);
                            int count = jArray.length();
                            for (int i = 0; i < count; i++) {
                                JSONObject jObject = jArray.getJSONObject(i);

                                needIDListAsString += jObject.getString("id") + "#";
                                needNameListAsString += jObject.getString("name") + "#";
                                needTelephoneListAsString  += jObject.getString("telephone") + "#";

                                needAddressListAsString += jObject.getString("address") + "#";

                                needCityListAsString += jObject.getString("city") + "#";
                                needTextListAsString += jObject.getString("needs") + "#";
                                needHeadCountListAsString  += jObject.getString("heads") + "#";

                                needCreatedAtListAsString += jObject.getString("created_at") + "#";
                                needUpdatedAtListAsString += jObject.getString("updated_at") + "#";

                                switch(jObject.getString("identifier").toLowerCase()){
                                    case "a" : // android
                                        needIdentifierListAsString += "Android#";
                                        break;

                                    case "b" : // bot
                                        needIdentifierListAsString += "BOT#";
                                        break;

                                    case "i" : // ios
                                        needIdentifierListAsString += "IOS#";
                                        break;

                                    case "w" : // site
                                        needIdentifierListAsString += "Web#";
                                        break;
                                }
                            }
                            return new String[][]{
                                    needIDListAsString.split("#"),
                                    needNameListAsString.split("#"),
                                    needTelephoneListAsString.split("#"),

                                    needCityListAsString.split("#"),
                                    needTextListAsString.split("#"),
                                    needHeadCountListAsString.split("#"),

                                    needCreatedAtListAsString.split("#"),
                                    needUpdatedAtListAsString.split("#"),
                                    needIdentifierListAsString.split("#"),

                                    needAddressListAsString.split("#")};
                        } catch (Exception ex) {
                            System.out.println("Ex " + ex.toString());
                            return null;
                        }
                    } catch (Exception ex) {
                        System.out.println("Ex " + ex.toString());
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return null;
        }
    }

    public String[][] getAllDonations(){
        String
                line,
                result,

                donationIDListAsString = "",
                donationNameListAsString = "",
                donationTelephoneListAsString = "",
                donationAddressListAsString = "",
                donationCityListAsString = "",
                donationTextListAsString = "",
                donationInformationListAsString = "",
                donationCreatedAtListAsString = "",
                donationUpdatedAtListAsString = "",
                donationIdentifierListAsString = "";

        InputStream is;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "getAllDonations.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                try {
                    conn.connect();
                    is = conn.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        result = Html.fromHtml(result).toString();
                        is.close();

                        try {
                            JSONArray jArray = new JSONArray(result);
                            int count = jArray.length();
                            for (int i = 0; i < count; i++) {
                                JSONObject jObject = jArray.getJSONObject(i);

                                donationIDListAsString += jObject.getString("id") + "#";
                                donationNameListAsString += jObject.getString("name") + "#";
                                donationTelephoneListAsString  += jObject.getString("telephone") + "#";

                                donationAddressListAsString += jObject.getString("address") + "#";

                                donationCityListAsString += jObject.getString("city") + "#";
                                donationTextListAsString += jObject.getString("donation") + "#";
                                donationInformationListAsString  += jObject.getString("information") + "#";

                                donationCreatedAtListAsString += jObject.getString("created_at") + "#";
                                donationUpdatedAtListAsString += jObject.getString("updated_at") + "#";


                                switch(jObject.getString("identifier").toLowerCase()){
                                    case "a" : // android
                                        donationIdentifierListAsString += "Android#";
                                        break;

                                    case "b" : // bot
                                        donationIdentifierListAsString += "BOT#";
                                        break;

                                    case "i" : // ios
                                        donationIdentifierListAsString += "IOS#";
                                        break;

                                    case "w" : // site
                                        donationIdentifierListAsString += "Web#";
                                        break;
                                }

                            }
                            return new String[][]{
                                    donationIDListAsString.split("#"),
                                    donationNameListAsString.split("#"),
                                    donationTelephoneListAsString.split("#"),

                                    donationCityListAsString.split("#"),
                                    donationTextListAsString.split("#"),
                                    donationInformationListAsString.split("#"),

                                    donationCreatedAtListAsString.split("#"),
                                    donationUpdatedAtListAsString.split("#"),
                                    donationIdentifierListAsString.split("#"),

                                    donationAddressListAsString.split("#")};
                        } catch (Exception ex) {
                            System.out.println("Ex " + ex.toString());
                            return null;
                        }
                    } catch (Exception ex) {
                        System.out.println("Ex " + ex.toString());
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return null;
        }
    }

    public boolean addNewDonation(String name, String telephone, String address, String city, String donation, String information){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "addNewDonation.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("name", name)
                        .appendQueryParameter("telephone", telephone)
                        .appendQueryParameter("address", address)
                        .appendQueryParameter("city", city)
                        .appendQueryParameter("donation", donation)
                        .appendQueryParameter("information", information)
                        .appendQueryParameter("identifier", "A");

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                try {
                    conn.connect();
                    return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return false;
        }
    }

    public boolean addDeviceToken(String token){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "addDeviceToken.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", token);

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                try {
                    conn.connect();
                    return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return false;
        }
    }

    public boolean addNewNeed(String name, String telephone, String address, String city, String needs, String heads){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVER_URL_PATH() + "addNewNeed.php");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("name", name)
                        .appendQueryParameter("telephone", telephone)
                        .appendQueryParameter("address", address)
                        .appendQueryParameter("city", city)
                        .appendQueryParameter("needs", needs)
                        .appendQueryParameter("heads", heads)
                        .appendQueryParameter("identifier", "A");

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                try {
                    conn.connect();
                    return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return false;
        }
    }
}
