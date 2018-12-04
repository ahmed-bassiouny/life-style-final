package lifestyle.com.lifestyle.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.AgeAdapter;
import lifestyle.com.lifestyle.adapter.HeightAdapter;
import lifestyle.com.lifestyle.adapter.IClickAdapter;
import lifestyle.com.lifestyle.adapter.WeightAdapter;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.controller.MBICalcController;
import lifestyle.com.lifestyle.custome_views.CircleProgressBar;
import lifestyle.com.lifestyle.dialog.BmiInfoDialog;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.helper.Equation;
import lifestyle.com.lifestyle.model.User;

public class MyMBICalcActivity extends BaseActivity {

    @BindView(R.id.custom_progressBar)
    CircleProgressBar progressBar;
    @BindView(R.id.text_progress)
    TextView text_progress;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.recycler_age)
    RecyclerView recyclerAge;
    @BindView(R.id.recycler_height)
    RecyclerView recyclerHeight;
    @BindView(R.id.recycler_weight)
    RecyclerView recyclerWeight;
    @BindView(R.id.iv_male)
    ImageView ivMale;
    @BindView(R.id.iv_female)
    ImageView ivFemale;
    @BindView(R.id.btn_calc)
    Button btn_calc;

    private MBICalcController controller;
    private int weight = 0;
    private int height = 0;
    private int age = 0;
    private boolean isMale = true;
    int pro = 1;

    private AgeAdapter ageAdapter;
    private HeightAdapter heightAdapter;
    private WeightAdapter weightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_mbicalc);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.my_bmi));
        controller = new MBICalcController(this);

        recyclerAge.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ageAdapter = new AgeAdapter(this, controller.getAges(), selectAge);
        recyclerAge.setAdapter(ageAdapter);

        recyclerHeight.setLayoutManager(new LinearLayoutManager(this));
        heightAdapter = new HeightAdapter(this, controller.getHeight(), selectHeight);
        recyclerHeight.setAdapter(heightAdapter);

        recyclerWeight.setLayoutManager(new LinearLayoutManager(this));
        weightAdapter = new WeightAdapter(this, controller.getWeight(), selectWeight);
        recyclerWeight.setAdapter(weightAdapter);
        setDataUI();

    }

    private void setDataUI() {
        User user = SharedPrefManager.getObject(Constants.USER, User.class);
        if (user.isMale()) {
            setMale();
        } else {
            setFemale();
        }
        try {
            heightAdapter.setHeight(Integer.parseInt(user.getHeight()));
        } catch (Exception e) {

        }
        try {
            weightAdapter.setWeight(Integer.parseInt(user.getCurrentWeight()));
        } catch (Exception e) {

        }
        try {

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);

            Calendar bithday = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            bithday.setTime(sdf.parse(user.getBirthday()));// all done
            int age = year - bithday.get(Calendar.YEAR);
            ageAdapter.setAge(age);

        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_calc.setVisibility(View.INVISIBLE);
        calcBMI();

    }

    public void setMale() {
        ivMale.setBackground(getDrawable(R.drawable.fill_white));
        ivFemale.setBackgroundColor(Color.TRANSPARENT);
        isMale = true;
    }

    @OnClick(R.id.iv_female)
    public void setFemale() {
        ivFemale.setBackground(getDrawable(R.drawable.fill_white));
        ivMale.setBackgroundColor(Color.TRANSPARENT);
        isMale = false;
    }

    public void calcBMI() {
        if (age == 0) {
            controller.showErrorMessage(getString(R.string.invalid_age));
        } else if (height == 0) {
            controller.showErrorMessage(getString(R.string.invalid_height));
        } else if (weight == 0) {
            controller.showErrorMessage(getString(R.string.invalid_weight));
        } else {
            float result = Equation.calBMI(weight, height);
            setBMI(result);
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText(controller.getBMIStatus(result));
            if (result < 18.5) {
                progressBar.changeColor(getResources().getColor(R.color.item1));
                tvStatus.setTextColor(getResources().getColor(R.color.item1));
            } else if (result >= 18.5 && result < 25) {
                progressBar.changeColor(getResources().getColor(R.color.item2));
                tvStatus.setTextColor(getResources().getColor(R.color.item2));
            } else if (result >= 25 && result < 30) {
                progressBar.changeColor(getResources().getColor(R.color.item3));
                tvStatus.setTextColor(getResources().getColor(R.color.item3));
            } else if (result >= 30 && result < 35) {
                progressBar.changeColor(getResources().getColor(R.color.item4));
                tvStatus.setTextColor(getResources().getColor(R.color.item4));
            } else if (result >= 35) {
                progressBar.changeColor(getResources().getColor(R.color.item5));
                tvStatus.setTextColor(getResources().getColor(R.color.item5));
            }
        }
    }

    private IClickAdapter<Integer> selectAge = new IClickAdapter<Integer>() {
        @Override
        public void click(Integer integer, int position) {
            age = integer;
            recyclerAge.scrollToPosition(position);
        }
    };

    private IClickAdapter<Integer> selectHeight = new IClickAdapter<Integer>() {
        @Override
        public void click(Integer integer, int position) {
            height = integer;
            recyclerHeight.scrollToPosition(position);
        }
    };

    private IClickAdapter<Integer> selectWeight = new IClickAdapter<Integer>() {
        @Override
        public void click(Integer integer, int position) {
            weight = integer;
            recyclerWeight.scrollToPosition(position);

        }
    };


    @OnClick(R.id.tv_status)
    public void showDialog() {
        new BmiInfoDialog(this).show();
    }

    private void setBMI(final float newVal) {
        pro = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pro < newVal) {
                    try {
                        Thread.sleep(50);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(pro);
                                text_progress.setText(String.valueOf(pro));
                            }
                        });
                        pro += 1;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
