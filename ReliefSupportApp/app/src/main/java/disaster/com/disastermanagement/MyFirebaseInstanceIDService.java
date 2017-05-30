package disaster.com.disastermanagement;

/**
 * Created by Sumudu on 6/22/2016.
 */

import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


//Class extending FirebaseInstanceIdService
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Displaying token on logcat

        SharedPreferences logcatStart = getSharedPreferences("userCredentials", 0);
        SharedPreferences.Editor logcatEditor = logcatStart.edit();
        logcatEditor.putString("userToken", refreshedToken);
        logcatEditor.apply();

        System.out.println("Token is: " + refreshedToken);
    }

    @Override
    public void onDestroy() {
    }
}