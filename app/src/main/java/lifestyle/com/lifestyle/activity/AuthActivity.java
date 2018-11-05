package lifestyle.com.lifestyle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import bassiouny.ahmed.genericmanager.MyFragmentTransaction;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.fragments.SignInFragment;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_auth);
        MyFragmentTransaction.open(this, new SignInFragment(), R.id.main_frame, null);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
