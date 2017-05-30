package disaster.com.disastermanagement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class MainActivity extends Activity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
    }

    @Override
    public void onBackPressed() {
        backButtonOption();
    }

    public void backButtonOption(){}
}
