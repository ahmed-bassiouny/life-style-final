package lifestyle.com.lifestyle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import bassiouny.ahmed.genericmanager.MyFragmentTransaction;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.fragments.SignInFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        MyFragmentTransaction.open(this,new SignInFragment(),R.id.main_frame,null);
    }
}
