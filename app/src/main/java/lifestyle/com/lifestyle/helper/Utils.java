package lifestyle.com.lifestyle.helper;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Utils {
    public static String checkString(String msg) {
        if (msg == null)
            msg = "";
        return msg;
    }

    // convert normal image as file to part for upload to server
    public static MultipartBody.Part convertFileToPart(File file) {
        if (file == null)
            return null;
        return MultipartBody.Part.createFormData("photo", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }

    public static RequestBody convertStringToPart(String str) {
        if (str == null)
            str = "";
        return RequestBody.create(okhttp3.MultipartBody.FORM, str);
    }

    public static int getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }
}
