package lifestyle.com.lifestyle.helper;

import android.content.Context;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.alarms.Alarm;
import lifestyle.com.lifestyle.model.User;
import lifestyle.com.lifestyle.model.WaterAlarmType;

public class DefaultValue {

    public static void waterAlarm(Context context) {
        if (SharedPrefManager.getObject(Constants.WATER_ALARM_TYPE, WaterAlarmType.class) == null) {
            SharedPrefManager.setObject(Constants.WATER_ALARM_TYPE, WaterAlarmType.TYPE12);
            Alarm.setAlarmForWater(context);
        }
    }

    public static void setCalory(User user){
        SharedPrefManager.setString(Constants.CALORY,Equation.calculateCalory(user));
    }
    public static int getCalory(){
        try {
            return Integer.parseInt(SharedPrefManager.getString(Constants.CALORY));
        }catch (Exception e){
            return 0;
        }
    }
}
