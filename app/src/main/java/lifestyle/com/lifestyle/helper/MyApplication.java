package lifestyle.com.lifestyle.helper;

import android.app.Application;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.api.RetrofitConfig;
import lifestyle.com.lifestyle.model.User;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefManager.init(this, "life_style");
        RetrofitConfig.initRetrofitConfig();

        // todo i will remove it
        User user = SharedPrefManager.getObject(Constants.USER, User.class);
        if (user == null) {
            user = new User();
            user.setId(1);
            SharedPrefManager.setObject(Constants.USER, user);
        }
    }
}
