package lifestyle.com.lifestyle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import bassiouny.ahmed.genericmanager.CustomNotificationManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.helper.Constants;

public class MyReceiverForMeal extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        CustomNotificationManager notifi = CustomNotificationManager
                .getInstance(context);
        notifi.setIcon(R.mipmap.ic_launcher);
        notifi.setBody(intent.getStringExtra(Constants.NOTIFICATION));
        notifi.show(1);
    }
}
