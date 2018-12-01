package lifestyle.com.lifestyle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

import bassiouny.ahmed.genericmanager.MyFragmentTransaction;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.fragments.SignInFragment;

public class AuthActivity extends BaseActivity {

    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1972;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_auth);
        checkGooglePlayServicesAvailability();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    public void checkGooglePlayServicesAvailability() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int statusCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (statusCode == ConnectionResult.SUCCESS) {
            MyFragmentTransaction.open(this, new SignInFragment(), R.id.main_frame, null);
        } else {
            apiAvailability.getErrorDialog(this, 1, REQUEST_GOOGLE_PLAY_SERVICES).show();
        }
    }
}
