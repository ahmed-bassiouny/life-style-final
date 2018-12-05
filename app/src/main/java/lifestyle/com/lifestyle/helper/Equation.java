package lifestyle.com.lifestyle.helper;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.model.User;

public class Equation {


    public static double calBMR(int weight,int height,String birthday,boolean isMale) {
        float result = 0;
       /*  user = SharedPrefManager.getObject(Constants.USER, User.class);
        float weight = Float.parseFloat(user.getCurrentWeight());
        float hight = Float.parseFloat(user.getHeight());*/
        Calendar c;
        try {
            c = getDateOfBirth(birthday);
        } catch (ParseException e) {
            return 0;
        }
        int age = Utils.getAge(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        if (isMale) {
            result = (float) (66 + (13.7 * weight) + (5 * height) - (6.8 * age));
        } else {
            result = (float) (655 + (9.6 * weight) + (1.8 * height) - (4.7 * age));
        }
        return result;
    }
    public static float calBMI(float w, float h) {
        float height = h / 100;
        return w / (height * height);
    }


    private static Calendar getDateOfBirth(String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimePicker.DATE_FORMAT, Locale.US);
        cal.setTime(sdf.parse(date));// all done
        return cal;
    }

    public static double getTEE(double bmr) {
        return bmr * getMotionCoefficient();
    }

    private static double getMotionCoefficient() {
        User user = SharedPrefManager.getObject(Constants.USER, User.class);
        switch (user.getWorkTypeKey()) {
            case 0:
                return 1.2;
            case 1:
                return 1.375;
            case 2:
                return 1.55;
            case 3:
                return 1.725;
            case 4:
                return 1.9;
        }
        return 0;
    }

    public static String calculateCalory(User user) {
        int calcResult ;
        double bmrResut = Equation.calBMR(Integer.parseInt(user.getCurrentWeight()),
                Integer.parseInt(user.getHeight()), user.getBirthday(), user.isMale());
        int tee = (int) Equation.getTEE(bmrResut);
        if (user.getPurposeKey() == 0) {
            calcResult = tee - 700;
        } else if (user.getPurposeKey() == 2) {
            calcResult = tee + 700;
        } else {
            calcResult = tee;
        }
        if (user.isMale() && calcResult < 1500) {
            calcResult = 1500;
        } else if (user.isFemale() && calcResult < 1200) {
            calcResult = 1200;
        }
        return String.valueOf(calcResult);
    }
}
