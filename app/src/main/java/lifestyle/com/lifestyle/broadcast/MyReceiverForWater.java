package lifestyle.com.lifestyle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
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
    }
}
