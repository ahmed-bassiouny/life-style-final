package lifestyle.com.lifestyle.broadcast;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

import bassiouny.ahmed.genericmanager.CustomNotificationManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.helper.Constants;

public class MyReceiverForMealHourl extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
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
                    .setContentText("نوتيفيكشن خاصة بالاكل");
        } else {
            mBuilder = new NotificationCompat.Builder(context, "lifeStyle")
//                .setContentText("4")

                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentTitle("LifeStyle")
                    .setContentText("نوتيفيكشن خاصة بالاكل");

        }

        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        mBuilder.setDefaults(defaults);
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(5, mBuilder.build());


        /*Calendar cal = Calendar.getInstance();
        Log.e("onReceive", "onReceive: ");
        Intent _intent = new Intent(context, MyReceiverForMealHourl.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, _intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        // Remove any previous pending intent.
        alarmManager.cancel(pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pendingIntent);*/
        /*
        switch (cal.get(Calendar.HOUR_OF_DAY)){
            case 8:
                notifi.setTitle("LifeStyle");
                notifi.setBody("وقت الفطار");
                notifi.show(1);
                break;
            case 11:
                notifi.setTitle("LifeStyle");
                notifi.setBody("يلا ناكس سناكس");
                notifi.show(1);
                break;
            case 14:
                notifi.setTitle("LifeStyle");
                notifi.setBody("وقت الغداء");
                notifi.show(1);
                break;
            case 17:
                notifi.setTitle("LifeStyle");
                notifi.setBody("يلا ناكس سناكس");
                notifi.show(1);
                break;
            case 20:
                notifi.setTitle("LifeStyle");
                notifi.setBody("وقت العشاء");
                notifi.show(1);
                break;
            case 23:
                notifi.setTitle("LifeStyle");
                notifi.setBody("ممكن ناكل زبادى");
                notifi.show(1);
                break;
        }*/
    }
}
