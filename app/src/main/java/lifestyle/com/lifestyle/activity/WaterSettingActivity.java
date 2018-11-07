package lifestyle.com.lifestyle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.alarms.Alarm;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.model.WaterAlarmType;

public class WaterSettingActivity extends BaseActivity {


    @BindView(R.id.linear_6)
    LinearLayout linear6;
    @BindView(R.id.linear_12)
    LinearLayout linear12;
    @BindView(R.id.linear_18)
    LinearLayout linear18;
    @BindView(R.id.linear_twice)
    LinearLayout linearTwice;
    @BindView(R.id.linear_cancel)
    LinearLayout linearCancel;


    @BindView(R.id.tv_60)
    TextView tv60;
    @BindView(R.id.tv_60_m)
    TextView tv60M;

    @BindView(R.id.tv_120)
    TextView tv120;
    @BindView(R.id.tv_120_m)
    TextView tv120M;

    @BindView(R.id.tv_180)
    TextView tv180;
    @BindView(R.id.tv_180_m)
    TextView tv180M;

    @BindView(R.id.tv_twice)
    TextView tvTwice;
    @BindView(R.id.tv_twice_m)
    TextView tvTwiceM;

    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    private WaterAlarmType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_water_setting);
        initToolbar(getString(R.string.water_program));
        ButterKnife.bind(this);
        type = SharedPrefManager.getObject(Constants.WATER_ALARM_TYPE, WaterAlarmType.class);
        setDataUI();
    }

    private void setDataUI() {
        if(type == null)
            return;
        switch (type) {
            case TYPE6:
                linear6.performClick();
                break;
            case TYPE12:
                linear12.performClick();
                break;
            case TYPE18:
                linear18.performClick();
                break;
            case TWICE:
                linearTwice.performClick();
                break;
            case CANCEL:
                linearCancel.performClick();
                break;
        }
    }

    @OnClick(R.id.btn_save)
    public void save() {
        SharedPrefManager.setObject(Constants.WATER_ALARM_TYPE, type);
        Alarm.setAlarmForWater(this);
        onBackPressed();
    }

    @OnClick({R.id.linear_6, R.id.linear_12, R.id.linear_18, R.id.linear_twice, R.id.linear_cancel})
    public void click(View view) {
        setOriginalBackground(linear6);
        setOriginalBackground(linear12);
        setOriginalBackground(linear18);
        setOriginalBackground(linearTwice);
        setOriginalBackground(linearCancel);

        setOriginalColor(tv60);
        setOriginalColor(tv60M);
        setOriginalColor(tv120);
        setOriginalColor(tv120M);
        setOriginalColor(tv180);
        setOriginalColor(tv180M);
        setOriginalColor(tvTwice);
        setOriginalColor(tvTwiceM);
        setOriginalColor(tvCancel);
        switch (view.getId()) {
            case R.id.linear_6:
                setSelectedView(linear6, tv60, tv60M);
                type = WaterAlarmType.TYPE6;
                break;
            case R.id.linear_12:
                setSelectedView(linear12, tv120, tv120M);
                type = WaterAlarmType.TYPE12;
                break;
            case R.id.linear_18:
                setSelectedView(linear18, tv180, tv180M);
                type = WaterAlarmType.TYPE18;
                break;
            case R.id.linear_twice:
                setSelectedView(linearTwice, tvTwice, tvTwiceM);
                type = WaterAlarmType.TWICE;
                break;
            case R.id.linear_cancel:
                setSelectedView(linearCancel, tvCancel, tvCancel);
                type = WaterAlarmType.CANCEL;
                break;
        }
    }

    private void setOriginalBackground(View view) {
        view.setBackground(getDrawable(R.drawable.fill_white));
    }

    private void setOriginalColor(TextView textView) {
        textView.setTextColor(getResources().getColor(R.color.light_black));
    }

    private void setSelectedView(LinearLayout linear, TextView textView1, TextView textView2) {
        linear.setBackground(getDrawable(R.drawable.fill_light_blue));
        textView1.setTextColor(getResources().getColor(R.color.white));
        textView2.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
