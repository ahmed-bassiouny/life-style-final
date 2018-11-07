package lifestyle.com.lifestyle.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
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
        tvCal.setText(" CAL " +SharedPrefManager.getString(Constants.CALORIES));
        ivFood.setOnClickListener(this);
        ivWater.setOnClickListener(this);
        ivBmi.setOnClickListener(this);
        ivAbout.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        ivLogout.setOnClickListener(this);

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
                //intent = new Intent(this,MBICalcActivity.class);
                break;
            case R.id.iv_profile:
                intent = new Intent(this, CalcCaloryActivity.class);
                break;
            case R.id.iv_logout:
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}
