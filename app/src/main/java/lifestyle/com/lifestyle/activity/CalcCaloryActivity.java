package lifestyle.com.lifestyle.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ycuwq.datepicker.date.DatePickerDialogFragment;

import java.util.Calendar;
import java.util.Locale;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.HeightAdapter;
import lifestyle.com.lifestyle.adapter.IClickAdapter;
import lifestyle.com.lifestyle.adapter.WeightAdapter;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.controller.MBICalcController;
import lifestyle.com.lifestyle.controller.RegisterController;
import lifestyle.com.lifestyle.custome_views.MyDatePickerDialogFragment;
import lifestyle.com.lifestyle.fragments.WorkTypeListDialogFragment;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.model.User;

public class CalcCaloryActivity extends BaseActivity {


    @BindView(R.id.et_birthday)
    TextInputEditText etBirthday;
    @BindView(R.id.et_work_type)
    TextInputEditText etWorkType;
    @BindView(R.id.et_goal)
    TextInputEditText etGoal;
    @BindView(R.id.recycler_height)
    RecyclerView recyclerHeight;
    @BindView(R.id.recycler_weight)
    RecyclerView recyclerWeight;
    @BindView(R.id.iv_male)
    ImageView ivMale;
    @BindView(R.id.iv_female)
    ImageView ivFemale;
    @BindView(R.id.btn_calc)
    Button btnCalc;
    @BindView(R.id.pbProgress)
    ProgressBar progressBar;


    private MBICalcController controller;
    private RegisterController registerController;
    private int weight = 0;
    private int height = 0;
    private boolean isMale = true;
    private int goalKey = 0;
    private int workTypeKey = 0;
    private HeightAdapter heightAdapter;
    private WeightAdapter weightAdapter;
    private boolean registerScreen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_calc_calory);
        initToolbar(getString(R.string.calc_calory));
        ButterKnife.bind(this);
        controller = new MBICalcController(this);
        registerController = new RegisterController(this);
        recyclerHeight.setLayoutManager(new LinearLayoutManager(this));
        heightAdapter = new HeightAdapter(this, controller.getHeight(), selectHeight);
        recyclerHeight.setAdapter(heightAdapter);

        recyclerWeight.setLayoutManager(new LinearLayoutManager(this));
        weightAdapter = new WeightAdapter(this, controller.getWeight(), selectWeight);
        recyclerWeight.setAdapter(weightAdapter);
        User user = SharedPrefManager.getObject(Constants.USER, User.class);
        if (!user.getBirthday().isEmpty()) {
            setDataUI(user);
            registerScreen = false;
        }
    }

    private void setDataUI(User user) {
        etBirthday.setText(user.getBirthday());
        if (user.isFemale()) {
            setFemale();
        } else {
            setMale();
        }
        // set weight
        int weight = Integer.parseInt(user.getCurrentWeight());
        int weightIndex = weight - Constants.MIN_WEIGHT;
        weightAdapter.clickOnItem(weightIndex);
        recyclerWeight.scrollToPosition(weightIndex);
        // set hieght
        int height = Integer.parseInt(user.getHeight());
        int heightIndex = height - Constants.MIN_HEIGHT;
        heightAdapter.clickOnItem(heightIndex);
        recyclerHeight.scrollToPosition(heightIndex);
        etWorkType.setText(getResources().getStringArray(R.array.work_type)[user.getWorkTypeKey()]);
        etGoal.setText(getResources().getStringArray(R.array.goal)[user.getPurposeKey()]);
    }

    @OnClick(R.id.et_birthday)
    public void selectBirthday() {
        MyDatePickerDialogFragment datePickerDialogFragment = new MyDatePickerDialogFragment();
        datePickerDialogFragment.showAnimation(true);
        datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");
        datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
            @Override
            public void onDateChoose(int year, int month, int day) {
                // formate is yyyy-MM-dd
                if ((Calendar.getInstance().get(Calendar.YEAR) - year ) >= 5)
                    etBirthday.setText(String.format(Locale.ENGLISH, "%d-%d-%d", year, month, day));
                else {
                    controller.showErrorMessage("يجب ان يكون العمر اكتر من ٥ سنوات");
                }
            }
        });
    }

    @OnClick(R.id.et_work_type)
    public void selectWorkType() {
        final WorkTypeListDialogFragment fragment = new WorkTypeListDialogFragment();
        Bundle args = new Bundle();
        args.putString(Constants.TYPE, Constants.WORK_TYPE);
        fragment.setArguments(args);
        fragment.mListener = new WorkTypeListDialogFragment.Listener() {
            @Override
            public void onSelectItem(int position) {
                etWorkType.setText(getResources().getStringArray(R.array.work_type)[position]);
                workTypeKey = position;
                fragment.dismiss();
            }
        };
        fragment.show(getSupportFragmentManager(), "tag1");

    }

    @OnClick(R.id.et_goal)
    public void selectGoal() {
        final WorkTypeListDialogFragment fragment = new WorkTypeListDialogFragment();
        Bundle args = new Bundle();
        args.putString(Constants.TYPE, Constants.GOAL);
        fragment.setArguments(args);
        fragment.mListener = new WorkTypeListDialogFragment.Listener() {
            @Override
            public void onSelectItem(int position) {
                etGoal.setText(getResources().getStringArray(R.array.goal)[position]);
                goalKey = position;
                fragment.dismiss();
            }
        };
        fragment.show(getSupportFragmentManager(), "tag2");

    }

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
    public void calcCalory() {
        if (etBirthday.getText().toString().isEmpty()) {
            controller.showErrorMessage(getString(R.string.invalid_birthday));
        } else if (height == 0) {
            controller.showErrorMessage(getString(R.string.invalid_height));
        } else if (weight == 0) {
            controller.showErrorMessage(getString(R.string.invalid_weight));
        } else if (etWorkType.getText().toString().isEmpty()) {
            controller.showErrorMessage(getString(R.string.work_type_required));
        } else if (etGoal.getText().toString().isEmpty()) {
            controller.showErrorMessage(getString(R.string.goals_required));
        } else {
            saveData();
        }
    }

    private void saveData() {
        User user = SharedPrefManager.getObject(Constants.USER, User.class);
        user.setHeight(String.valueOf(height));
        user.setCurrentWeight(String.valueOf(weight));
        user.setBirthday(etBirthday.getText().toString());
        if (isMale) {
            user.setMale();
        } else {
            user.setFemale();
        }
        user.setWorkType(String.valueOf(workTypeKey));
        user.setPurpose(String.valueOf(goalKey));
        if (registerScreen)
            registerController.register(user);
        else
            registerController.editProfile(user);
    }


    @Override
    public void startLoading() {
        btnCalc.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        btnCalc.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
