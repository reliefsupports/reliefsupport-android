package disaster.com.disastermanagement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by WAKENSYS on 5/29/2017.
 */

public class LoginPageActivity extends Activity {
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        context = LoginPageActivity.this;


    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){}
}
