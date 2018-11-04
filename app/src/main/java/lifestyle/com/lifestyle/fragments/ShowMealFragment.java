package lifestyle.com.lifestyle.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.MealAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMealFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.pbProgress)
    ProgressBar pbProgress;

    private MealAdapter adapter;
    private String mealType = "";

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
        if (getArguments() != null)
            mealType = getArguments().getString("type");
        recycler.setAdapter(adapter);
    }

    @OnClick(R.id.btn_another_meal)
    void onBtnAnotherMealClick() {
        /*
         * Animation RightSwipe = AnimationUtils.loadAnimation(Screen.this, R.anim.right_swipe);
         * ScreenAnimation.startAnimation(RightSwipe);*/
    }

}
