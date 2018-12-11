package lifestyle.com.lifestyle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import bassiouny.ahmed.genericmanager.CustomNotificationManager;
import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.helper.Equation;
import lifestyle.com.lifestyle.model.User;

public class MyReceiverForCalory extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        User user = SharedPrefManager.getObject(Constants.USER,User.class);
        if(user != null)
            SharedPrefManager.setString(Constants.CURRENT_CALORY, Equation.calculateCalory(user));
    }
}
