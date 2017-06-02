package disaster.com.disastermanagement;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Sumudu on 6/22/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
            String mainValue = remoteMessage.getData().get("mainValue");
            String subValue1 = remoteMessage.getData().get("subValue1");
            String subValue2 = remoteMessage.getData().get("subValue2");
            String subValue3 = remoteMessage.getData().get("subValue3");
            String subValue4 = remoteMessage.getData().get("subValue4");

                SharedPreferences sp = getSharedPreferences("notifications", 0);
                SharedPreferences.Editor spEditor = sp.edit();

                spEditor.putString("mainValue", mainValue + "#" + sp.getString("mainValue", ""));
                spEditor.putString("subValue1", subValue1 + "#" + sp.getString("subValue1", ""));
                spEditor.putString("subValue2", subValue2 + "#" + sp.getString("subValue2", ""));
                spEditor.putString("subValue3", subValue3 + "#" + sp.getString("subValue3", ""));
                spEditor.putString("subValue4", subValue4 + "#" + sp.getString("subValue4", ""));
                spEditor.putString("dateTime", getDateTime() + "#" + sp.getString("dateTime", ""));
                spEditor.apply();

                Intent intent = new Intent(this, SplashScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.alarm_light)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

                NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
                bigTextStyle.setBigContentTitle(mainValue);
                bigTextStyle.bigText(subValue1 + '\n' + subValue2 + '\n' + subValue3 + '\n' + subValue4);

                notificationBuilder.setStyle(bigTextStyle);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificationBuilder.build());


       /* NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, buildNotification(simNumber, carrierName).build());*/
    }

    @Override
    public void onDestroy() {
        Intent i = new Intent(MyFirebaseMessagingService.this, MyFirebaseMessagingService.class);
        startService(i);
    }

  /*  private RemoteViews getComplexNotificationView(String simNumber, String carrierName) {
        RemoteViews contentView = new RemoteViews(getPackageName(),R.layout.bar_notification);
        contentView.setImageViewResource(R.id.notification_image,R.drawable.final_icon);
        contentView.setTextViewText(R.id.notification_title, "activate ME");
        contentView.setTextViewText(R.id.notification_text, "Activated " + simNumber + " " + carrierName);
        contentView.setTextViewText(R.id.notification_text_second_line, simNumber);
        contentView.setTextViewText(R.id.notification_text_third_line, carrierName);
        return contentView;
    }

    protected NotificationCompat.Builder buildNotification(String simNumber, String carrierName) {
        Intent intent = new Intent(this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Open NotificationView.java Activity
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.final_icon)
                .setTicker("Activated" + '\n' + '\r' + simNumber + '\n' + '\r' + carrierName)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder = builder.setContent(getComplexNotificationView(simNumber, carrierName));
        } else {
            builder = builder.setContentTitle("activate ME").setContentText("Activated" + '\n' + '\r' + simNumber + '\n' + '\r' + carrierName).setSmallIcon(R.drawable.final_icon);
        }
        return builder;
    }*/

    public String getDateTime() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }
}
