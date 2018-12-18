package lifestyle.com.lifestyle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import bassiouny.ahmed.genericmanager.CustomNotificationManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.helper.Constants;

public class MyReceiverForMealHourl extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        CustomNotificationManager notifi = CustomNotificationManager
                .getInstance(context);
        notifi.setIcon(R.mipmap.ic_launcher);
        Calendar cal = Calendar.getInstance();
        switch (cal.get(Calendar.HOUR_OF_DAY)){
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
        }
    }
}
