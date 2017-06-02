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

    String lan = "";

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
                        Intent i1 = new Intent(context, MainMenuActivity.class);
                        context.startActivity(i1);
                        activity.finish();
                        break;

                    case 1 : // Emergency Numbers
                        Intent i2 = new Intent(context, ViewEmergencyNumbersActivity.class);
                        context.startActivity(i2);
                        activity.finish();
                        break;

                    case 2 : // notifications
                        Intent i3 = new Intent(context, ViewNotificationsActivity.class);
                        context.startActivity(i3);
                        activity.finish();
                        break;

                    case 3 : // Change language
                        Intent i4 = new Intent(context, LanguageChangeActivity.class);
                        context.startActivity(i4);
                        activity.finish();
                        break;

                    case 4 : // Exit Section
                        SharedPreferences sp1 = context.getSharedPreferences("language", 0);
                        lan += sp1.getString("lan", "1");
                        switch (lan){
                            case "1" :
                                new AlertDialog.Builder(context)
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
                                new AlertDialog.Builder(context)
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
                                new AlertDialog.Builder(context)
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
                        break;
                }
            }
        });

        navigationItemNameDisplay.setText(navigationItemNameList[position]);
        return gvContent;
    }
}
