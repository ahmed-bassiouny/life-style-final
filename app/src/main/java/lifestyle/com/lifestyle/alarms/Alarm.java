package lifestyle.com.lifestyle.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import bassiouny.ahmed.genericmanager.DateTimeManager;
import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.broadcast.MyReceiverForCalory;
import lifestyle.com.lifestyle.broadcast.MyReceiverForMeal;
import lifestyle.com.lifestyle.broadcast.MyReceiverForMealHourl;
import lifestyle.com.lifestyle.broadcast.MyReceiverForWater;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.model.User;
import lifestyle.com.lifestyle.model.WaterAlarmType;

public class Alarm {


    public static void setAlarmForWater(Context context) {
        cancelPendindIntentForWater(context);
        WaterAlarmType type = SharedPrefManager.getObject(Constants.WATER_ALARM_TYPE, WaterAlarmType.class);
        switch (type) {
            case TYPE6:
                setAlarmForWaterAfterTime(context, 1);
                break;
            case TYPE12:
                setAlarmForWaterAfterTime(context, 2);
                break;
            case TYPE18:
                setAlarmForWaterAfterTime(context, 3);
                break;
            case TWICE:
                setAlarmForWater2PM(context);
                setAlarmForWater8PM(context);
                break;
            case CANCEL:
                cancelPendindIntentForWater(context);
                break;

        }
    }

    private static void setAlarmForWaterAfterTime(Context context, int time) {
        Calendar calendar = Calendar.getInstance();
        // handle next day case
        // if hour = 23
        //get day and increase by one
        if (calendar.get(Calendar.HOUR_OF_DAY) == 23)
            calendar.set(Calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH) + 1));
        calendar.set(Calendar.HOUR_OF_DAY, (calendar.get(Calendar.HOUR_OF_DAY) + time));

        Intent intentAlarm = new Intent(context, MyReceiverForWater.class);
        // create the object
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), (time * AlarmManager.INTERVAL_HOUR), PendingIntent.getBroadcast(context, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    private static void setAlarmForWater2PM(Context context) {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 14)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        Intent intentAlarm = new Intent(context, MyReceiverForWater.class);
        // create the object
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 2, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    private static void setAlarmForWater8PM(Context context) {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 20)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        Intent intentAlarm = new Intent(context, MyReceiverForWater.class);
        // create the object
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 3, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public static void cancelPendindIntentForWater(Context context) {
        Intent intentAlarm = new Intent(context, MyReceiverForWater.class);
        PendingIntent.getBroadcast(context, 1, intentAlarm,
                PendingIntent.FLAG_UPDATE_CURRENT).cancel();
        PendingIntent.getBroadcast(context, 2, intentAlarm,
                PendingIntent.FLAG_UPDATE_CURRENT).cancel();
        PendingIntent.getBroadcast(context, 3, intentAlarm,
                PendingIntent.FLAG_UPDATE_CURRENT).cancel();
    }

    public static void setAlarmForMeal(Context context) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);

        Intent intentAlarm = new Intent(context, MyReceiverForMeal.class);
        // create the object
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // add on day 8 am
        cal.add(Calendar.DAY_OF_MONTH, 1);
        intentAlarm.putExtra(Constants.NOTIFICATION, context.getString(R.string.notification_breakfast));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 10, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        // add hours 11 am
        cal.add(Calendar.HOUR_OF_DAY, 3);
        intentAlarm.putExtra(Constants.NOTIFICATION, context.getString(R.string.notification_snacks));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 11, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        // add hours 2 pm
        cal.add(Calendar.HOUR_OF_DAY, 3);
        intentAlarm.putExtra(Constants.NOTIFICATION, context.getString(R.string.notification_lunch));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 12, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        // add hours 5pm
        cal.add(Calendar.HOUR_OF_DAY, 3);
        intentAlarm.putExtra(Constants.NOTIFICATION, context.getString(R.string.notification_snacks));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 13, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        // add hours 8pm
        cal.add(Calendar.HOUR_OF_DAY, 3);
        intentAlarm.putExtra(Constants.NOTIFICATION, context.getString(R.string.notification_dinner));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 14, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        // add hours 11pm
        cal.add(Calendar.HOUR_OF_DAY, 3);
        intentAlarm.putExtra(Constants.NOTIFICATION, "ممكن ناكل زبادى");
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 15, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }


    public static void setEveryHourAlarm(Context context) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, 0);

        Intent intentAlarm = new Intent(context, MyReceiverForMealHourl.class);
        // create the object
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, PendingIntent.getBroadcast(context, 10, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public static void resetCalory(Context context) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, 1);

        Intent intentAlarm = new Intent(context, MyReceiverForCalory.class);
        // create the object
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(context, 16, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }

}