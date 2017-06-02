package disaster.com.disastermanagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by WAKENSYS on 5/30/2017.
 */

public class ViewNeedCountChartActivity extends Activity {
    Data data = new Data();
    DBOperations dbOperations = new DBOperations();
    NetworkStatChecker n = new NetworkStatChecker();

    Context context;

    ImageView backButton;

    LineChart needChart;
    LineData needData;
    LineDataSet needDataSet;

    ArrayList<Entry> needCountList = new ArrayList<>();
    ArrayList<String> methodNameList = new ArrayList<>();

    TextView hiddenLayout;

    String lan = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_need_count_chart_layout);
        context = ViewNeedCountChartActivity.this;

        SharedPreferences sp = getSharedPreferences("language", 0);
        lan += sp.getString("lan", "1");

        needChart = (LineChart) findViewById(R.id.needChart);
        hiddenLayout = (TextView) findViewById(R.id.hiddenLayout);

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOption();
            }
        });

        new GetNeedCountDetails().execute();
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

    class GetNeedCountDetails extends AsyncTask<String, Void, String[][]> {
        boolean internetAvailable = false;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            switch (lan){
                case "1" :
                    progressDialog = ProgressDialog.show(context, "Loading", "Please wait");
                    break;

                case "2" :
                    progressDialog = ProgressDialog.show(context, "දත්ත ලැබෙමින් පවතී", "මඳක් ඉවසන්න");
                    break;

                case "3" :
                    progressDialog = ProgressDialog.show(context, "Loading", "தயவு செய்து காத்திருங்கள்");
                    break;
            }
        }

        @Override
        protected String[][] doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                String[][] res = dbOperations.getNeedCountDetails();
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
                    String[] needMethod = result[0];
                    String[] needCount = result[1];

                    needCountList.clear();
                    methodNameList.clear();

                    for(int k = 0; k < needMethod.length; k++){
                        needCountList.add(new Entry(Integer.parseInt(needCount[k]), k));
                        methodNameList.add(needMethod[k]);
                    }

                    needDataSet = new LineDataSet(needCountList, "");
                    needDataSet.setValueTextColor(Color.parseColor("#ff8000"));
                    needDataSet.setColor(Color.parseColor("#ff8000"));
                    needDataSet.setFillColor(Color.parseColor("#FF8000"));

                    needData = new LineData(methodNameList, needDataSet);
                    needData.setValueTextColor(Color.parseColor("#FF8000"));

                    needDataSet.setColors(ColorTemplate.JOYFUL_COLORS); //
                    needDataSet.setDrawCubic(true);
                    needDataSet.setDrawFilled(true);

                    needChart.setData(needData);

                    XAxis xAxis = needChart.getXAxis();
                    xAxis.setTextColor(Color.parseColor("#FF8000"));

                    YAxis yAxis1 = needChart.getAxisLeft();
                    yAxis1.setTextColor(Color.parseColor("#FF8000"));

                    YAxis yAxis2 = needChart.getAxisRight();
                    yAxis2.setTextColor(Color.parseColor("#FF8000"));

                    needChart.animateY(3000);
                    switch (lan){
                        case "1" :
                            needChart.setDescription("Number of donations according to the request method");
                            break;

                        case "2" :
                            needChart.setDescription("මාධ්\u200Dය අනුව අවශ්යතා ගණන");
                            break;

                        case "3" :
                            needChart.setDescription("Number of requirements according to the request method");
                            break;
                    }
                    needChart.setDescriptionTextSize(12f);
                    needChart.setDescriptionColor(Color.parseColor("#ffffff"));
                    needChart.setGridBackgroundColor(Color.TRANSPARENT);

                    hiddenLayout.setVisibility(View.GONE);
                    needChart.setVisibility(View.VISIBLE);
                }
                catch (Exception e){
                    if(this.internetAvailable){
                        hiddenLayout.setVisibility(View.VISIBLE);
                        needChart.setVisibility(View.GONE);
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
                        hiddenLayout.setVisibility(View.VISIBLE);
                        needChart.setVisibility(View.GONE);
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

            else if(this.internetAvailable){
                hiddenLayout.setVisibility(View.VISIBLE);
                needChart.setVisibility(View.GONE);
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
                hiddenLayout.setVisibility(View.VISIBLE);
                needChart.setVisibility(View.GONE);
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

            try{
                progressDialog.dismiss();
            }
            catch(Exception ignored){}
        }
    }
}
