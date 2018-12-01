package lifestyle.com.lifestyle.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.FoodAdapter;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.base.ui.BaseFragment;
import lifestyle.com.lifestyle.controller.FoodProgramController;
import lifestyle.com.lifestyle.model.Meal;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMealFragment extends BaseFragment implements BaseController.IResult<List<Meal>> {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.pbProgress)
    ProgressBar pbProgress;
    @BindView(R.id.btn_another_meal)
    Button btnAnotherMeal;
    @BindView(R.id.linear_hint)
    LinearLayout linearHint;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_hint_name)
    TextView tvHintN;

    private FoodAdapter adapter;
    private String mealType = "";
    private FoodProgramController controller;
    private List<Meal> meals;
    private int currentMeal = 0;

    public ShowMealFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_meal, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            mealType = getArguments().getString("type");
            adapter = new FoodAdapter(mContext);
            recycler.setAdapter(adapter);
            controller = new FoodProgramController(getMyActivity(), this);
            controller.getMeals(mealType, this);
        }
    }

    @OnClick(R.id.btn_another_meal)
    void onBtnAnotherMealClick() {

        Animation RightSwipe = AnimationUtils.loadAnimation(mContext, R.anim.left_swipe);
        recycler.startAnimation(RightSwipe);
        currentMeal++;
        if (currentMeal >= meals.size()) {
            currentMeal = 0;
        }
        setFoodList();
    }

    @Override
    public void startLoading() {
        recycler.setVisibility(View.GONE);
        btnAnotherMeal.setVisibility(View.GONE);
        linearHint.setVisibility(View.GONE);
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        recycler.setVisibility(View.VISIBLE);
        pbProgress.setVisibility(View.GONE);

    }

    @Override
    public void result(List<Meal> meals) {
        btnAnotherMeal.setVisibility(View.VISIBLE);
        linearHint.setVisibility(View.VISIBLE);
        this.meals = meals;
        setFoodList();
    }

    private void setFoodList() {
        if (meals.size() == 0){
            controller.showMessage("ﻻ يوجد وجبات");
            tvHintN.setVisibility(View.GONE);
            tvHint.setVisibility(View.GONE);
            return;
        }
        adapter.setList(meals.get(currentMeal).getFoods());
        tvHint.setText(String.format(Locale.getDefault(), " %s %s ", meals.get(currentMeal).getCaloriesCount(), getString(R.string.calory)));
    }
}
