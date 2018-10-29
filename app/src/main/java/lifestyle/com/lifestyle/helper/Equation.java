package lifestyle.com.lifestyle.helper;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.model.User;

public class Equation {


    public static double getBMR() {
        double result = 0;
        User user = SharedPrefManager.getObject(Constants.USER, User.class);
        float weight = Float.parseFloat(user.getCurrentWeight());
        float hight = Float.parseFloat(user.getHeight());
        Calendar c;
        try {
            c = getDateofbieth(user.getBirthday());
        } catch (ParseException e) {
            return 0;
        }
        int age = Utils.getAge(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        Log.e("getBMR: ", age + "");
        if (user.isMale()) {
            result = 66 + (13.7 * weight) + (5 * hight) - (6.8 * age);
        } else if (user.isFemale()) {
            result = 655 + (9.6 * weight) + (1.8 * hight) - (4.7 * age);
        }
        return result;
    }

    private static Calendar getDateofbieth(String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimePicker.DATE_FORMAT, Locale.US);
        cal.setTime(sdf.parse(date));// all done
        return cal;
    }

    public static double getTEE() {
        return getMotionCoefficient() * getBMR();
    }

    private static double getMotionCoefficient() {
        User user = SharedPrefManager.getObject(Constants.USER, User.class);
        switch (user.getWorkTypeKey()) {
            case 1:
                return 1.2;
            case 2:
                return 1.375;
            case 3:
                return 1.55;
            case 4:
                return 1.725;
            case 5:
                return 1.9;
        }
        return 0;
    }
}
