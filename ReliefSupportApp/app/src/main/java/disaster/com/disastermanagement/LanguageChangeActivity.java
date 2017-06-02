package disaster.com.disastermanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by WAKENSYS on 6/2/2017.
 */

public class LanguageChangeActivity extends Activity {
    Context context;

    CardView tamilButton, sinhalaButton, englishButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_activity_layout);
        context = LanguageChangeActivity.this;

        englishButton = (CardView) findViewById(R.id.englishButton);
        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("language", 0);
                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString("lan", "1");
                spEditor.apply();

                BackButtonOption();
            }
        });

        sinhalaButton = (CardView) findViewById(R.id.sinhalaButton);
        sinhalaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("language", 0);
                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString("lan", "2");
                spEditor.apply();

                BackButtonOption();
            }
        });

        tamilButton = (CardView) findViewById(R.id.tamilButton);
        tamilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("language", 0);
                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString("lan", "3");
                spEditor.apply();

                BackButtonOption();
            }
        });
    }

    @Override
    public void onBackPressed() {
        BackButtonOption();
    }

    public void BackButtonOption(){
        Intent i = new Intent(context, MainMenuActivity.class);
        startActivity(i);
        finish();
    }
}
