package disaster.com.disastermanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CustomNavigationListAdapter extends BaseAdapter {
    Context context;
    Activity activity;

    private String[] navigationItemNameList;
    private int[] navigationItemIconList;

    public CustomNavigationListAdapter(Context context, Activity activity, String[] navigationItemNameList, int[] navigationItemIconList){
        this.context = context;
        this.activity = activity;

        this.navigationItemNameList = navigationItemNameList;
        this.navigationItemIconList = navigationItemIconList;
    }

    @Override
    public int getCount() {
        return navigationItemNameList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gvContent = inflater.inflate(R.layout.navigation_item_layout, null, true);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        gvContent.startAnimation(animation);

        final TextView navigationItemNameDisplay = (TextView) gvContent.findViewById(R.id.navigationItemNameDisplay);
        final ImageView navigationItemIconDisplay = (ImageView) gvContent.findViewById(R.id.navigationItemIconDisplay);

        Picasso
                .with(context)
                .load(navigationItemIconList[position])
                .fit()
                .into(navigationItemIconDisplay, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

        final LinearLayout navigationItemButton = (LinearLayout) gvContent.findViewById(R.id.navigationItemButton);
        navigationItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = context.getSharedPreferences("userCredentials", 0);
                String userCategoryID = sp.getString("userCategoryID", "");

                switch (position){
                    case 0 :  // Home Section
                        Intent i2 = new Intent(context, MainMenuActivity.class);
                        context.startActivity(i2);
                        activity.finish();
                        break;

                    case 1 : // notifications
                        Intent i = new Intent(context, ViewNotificationsActivity.class);
                        context.startActivity(i);
                        activity.finish();

                        break;

                    case 2 : // Logout Section
                        new AlertDialog.Builder(context)
                                .setTitle("Exit")
                                .setMessage("Do you really want to exit?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        SharedPreferences sp = context.getSharedPreferences("userCredentials", 0);
                                        SharedPreferences.Editor spEditor = sp.edit();

                                        spEditor.putBoolean("userLogged", false);
                                        spEditor.putString("userID", "");
                                        spEditor.putString("userCategoryID", "");
                                        spEditor.putString("userFirstName", "");
                                        spEditor.putString("userLastName", "");
                                        spEditor.putString("userImage", "");
                                        spEditor.apply();

                                       /* SharedPreferences sp2 = context.getSharedPreferences("notifications", 0);
                                        SharedPreferences.Editor spEditor2 = sp2.edit();

                                        spEditor2.putString("mainValue", "");
                                        spEditor2.putString("subValue1", "");
                                        spEditor2.putString("subValue2", "");
                                        spEditor2.putString("subValue3", "");
                                        spEditor2.putString("subValue4", "");
                                        spEditor2.putString("dateTime", "");
                                        spEditor2.apply();*/

                                        System.exit(0);
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();
                        break;
                }
            }
        });

        navigationItemNameDisplay.setText(navigationItemNameList[position]);
        return gvContent;
    }
}
