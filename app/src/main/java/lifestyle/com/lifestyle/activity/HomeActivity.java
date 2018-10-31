package lifestyle.com.lifestyle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.base.ui.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_home);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
