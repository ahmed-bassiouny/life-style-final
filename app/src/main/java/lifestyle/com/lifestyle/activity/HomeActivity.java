package lifestyle.com.lifestyle.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.alarms.Alarm;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.helper.DefaultValue;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_cal)
    TextView tvCal;
    @BindView(R.id.iv_food)
    ImageView ivFood;
    @BindView(R.id.iv_water)
    ImageView ivWater;
    @BindView(R.id.iv_bmi)
    ImageView ivBmi;
    @BindView(R.id.iv_about)
    ImageView ivAbout;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.iv_logout)
    ImageView ivLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        // set default value
        DefaultValue.waterAlarm(this);
        ivFood.setOnClickListener(this);
        ivWater.setOnClickListener(this);
        ivBmi.setOnClickListener(this);
        ivAbout.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        ivLogout.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // set it here to update value
        tvCal.setText(SharedPrefManager.getString(Constants.CALORIES) + " CAL ");
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_food:
                intent = new Intent(this, MealActivity.class);
                break;
            case R.id.iv_water:
                intent = new Intent(this, WaterSettingActivity.class);
                break;
            case R.id.iv_bmi:
                intent = new Intent(this, MBICalcActivity.class);
                break;
            case R.id.iv_about:
                Toast.makeText(this, "not available now", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_profile:
                intent = new Intent(this, CalcCaloryActivity.class);
                break;
            case R.id.iv_logout:

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(this);
                }
                builder
                        .setMessage("هل تريد تسجيل الخروج")
                        .setPositiveButton("تاكيد", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                        .requestEmail()
                                        .build();
                                GoogleSignIn.getClient(HomeActivity.this, gso).signOut();
                                SharedPrefManager.clearSharedPref();
                                startActivity(new Intent(HomeActivity.this, AuthActivity.class));
                                Alarm.cancelPendindIntentForWater(HomeActivity.this);
                                finish();
                            }
                        })
                        .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}
