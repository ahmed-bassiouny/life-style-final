package lifestyle.com.lifestyle.broadcast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

import bassiouny.ahmed.genericmanager.CustomNotificationManager;
import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.alarms.Alarm;
import lifestyle.com.lifestyle.helper.Constants;

public class MyReceiverForWater extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       /* if (SharedPrefManager.getBoolean(Constants.SHOW_NOTIFICATION_WATER)) {
            Intent newIntent = new Intent(context, WaterNotificationDialogActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newIntent);
        }*/

        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.HOUR_OF_DAY) >= 1 && cal.get(Calendar.HOUR_OF_DAY) < 8)
            return;
        String content = "يلا نشرب ماء";
        String CHANNEL_ID = "your_name";// The id of the channel.
        CharSequence name = context.getResources().getString(R.string.app_name);// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationCompat.Builder mBuilder;

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mNotificationManager.createNotificationChannel(mChannel);
            mBuilder = new NotificationCompat.Builder(context, "lifeStyle")
//                .setContentText("4")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId(CHANNEL_ID)
                    .setContentTitle("LifeStyle")
                    .setContentText(content);
        } else {
            mBuilder = new NotificationCompat.Builder(context, "lifeStyle")
//                .setContentText("4")

                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentTitle("LifeStyle")
                    .setContentText(content);

        }

        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        mBuilder.setDefaults(defaults);
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(6, mBuilder.build());
    }

}
