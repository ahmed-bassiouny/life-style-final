package lifestyle.com.lifestyle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
        CustomNotificationManager customNotificationManager = CustomNotificationManager.getInstance(context);
        customNotificationManager.setIcon(R.mipmap.ic_launcher);
        customNotificationManager.setTitle(context.getString(R.string.app_name));
        customNotificationManager.setBody("يلا نشرب ماء");
        customNotificationManager.show(1);
    }

}
