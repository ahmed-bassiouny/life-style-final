package lifestyle.com.lifestyle.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bassiouny.ahmed.genericmanager.MyFragmentTransaction;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.activity.CreateMealActivity;
import lifestyle.com.lifestyle.base.ui.BaseFragment;
import lifestyle.com.lifestyle.dialog.ChooseMealTypeDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectMealTypeFragment extends BaseFragment {


    public SelectMealTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_meal_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @OnClick({R.id.cv_breakfast, R.id.cr_lunch, R.id.cr_dinner, R.id.cv_snacks})
    public void onCLick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("type", view.getTag().toString());
        ShowMealFragment fragment = new ShowMealFragment();
        fragment.setArguments(bundle);
        MyFragmentTransaction.open(getMyActivity(), fragment, R.id.main_frame, "tag");
    }

    @OnClick(R.id.btn_create_meal)
    public void createMeal() {
        //startActivity(new Intent(mContext, CreateMealActivity.class));
        ChooseMealTypeDialog dialog = new ChooseMealTypeDialog(mContext);
        dialog.show();
    }
}
