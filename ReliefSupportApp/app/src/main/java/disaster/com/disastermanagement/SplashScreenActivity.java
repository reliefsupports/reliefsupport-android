package disaster.com.disastermanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by WAKENSYS on 5/29/2017.
 */

public class SplashScreenActivity extends Activity {
    Context context;
    NetworkStatChecker n = new NetworkStatChecker();
    DBOperations dbOperations = new DBOperations();

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        context = SplashScreenActivity.this;

        imageView = (ImageView) findViewById(R.id.imageView);

        Animation anim = AnimationUtils.loadAnimation(context, R.anim.blink);
        imageView.startAnimation(anim);

        new SendDeviceToken().execute();
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){
        System.exit(0);
    }

    class SendDeviceToken extends AsyncTask<String, Void, Boolean> {
        String userToken;
        boolean internetAvailable = false;

        @Override
        protected void onPreExecute() {
            SharedPreferences sp = getSharedPreferences("userCredentials", 0);
            userToken = sp.getString("userToken", "");
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            if(n.isConnected(context)){
                this.internetAvailable = true;
                boolean res = dbOperations.addDeviceToken(userToken);
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
                Intent i = new Intent(context, MainMenuActivity.class);
                startActivity(i);
                finish();
            }

            else if(this.internetAvailable){
                Toast.makeText(context, "Unable to add the device token", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
