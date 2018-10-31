package lifestyle.com.lifestyle.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
import lifestyle.com.lifestyle.helper.Equation;

public class MBICalcActivity extends BaseActivity {

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

    private MBICalcController controller;
    private int weight = 0;
    private int height = 0;
    private int age = 0;
    private boolean isMale = true;
    int pro = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_mbicalc);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.bmi));
        controller = new MBICalcController(this);

        recyclerAge.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerAge.setAdapter(new AgeAdapter(this, controller.getAges(), selectAge));

        recyclerHeight.setLayoutManager(new LinearLayoutManager(this));
        recyclerHeight.setAdapter(new HeightAdapter(this, controller.getHeight(), selectHeight));

        recyclerWeight.setLayoutManager(new LinearLayoutManager(this));
        recyclerWeight.setAdapter(new WeightAdapter(this, controller.getWeight(), selectWeight));

    }

    @OnClick(R.id.iv_male)
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

    @OnClick(R.id.btn_calc)
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
            tvStatus.setText(controller.getBMIStatus(result));
        }
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

    private IClickAdapter<Integer> selectAge = new IClickAdapter<Integer>() {
        @Override
        public void click(Integer integer, int position) {
            age = integer;
        }
    };

    private IClickAdapter<Integer> selectHeight = new IClickAdapter<Integer>() {
        @Override
        public void click(Integer integer, int position) {
            height = integer;
        }
    };

    private IClickAdapter<Integer> selectWeight = new IClickAdapter<Integer>() {
        @Override
        public void click(Integer integer, int position) {
            weight = integer;
        }
    };

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
