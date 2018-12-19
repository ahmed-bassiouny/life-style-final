package lifestyle.com.lifestyle.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.alarms.Alarm;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.model.User;

public class SplashActivity extends BaseActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = SharedPrefManager.getObject(Constants.USER, User.class);
                if (user != null) {
                    if (user.getBirthday().isEmpty())
                        intent = new Intent(SplashActivity.this, CalcCaloryActivity.class);
                    else
                        intent = new Intent(SplashActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, AuthActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
