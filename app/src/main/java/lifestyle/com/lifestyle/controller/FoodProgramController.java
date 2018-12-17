package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.List;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.helper.DefaultValue;
import lifestyle.com.lifestyle.interactor.IMealsInteractor;
import lifestyle.com.lifestyle.interactor.MealsInteractor;
import lifestyle.com.lifestyle.model.CustomMeal;
import lifestyle.com.lifestyle.model.Meal;

public class FoodProgramController extends BaseController {
    private IMealsInteractor interactor;
    private IResult<List<Meal>> result;
    private IResult<List<CustomMeal>> result2;

    public FoodProgramController(Activity activity, Fragment fragment) {
        super(activity, fragment);
        interactor = new MealsInteractor();
    }

    public void getMeals(String meals, IResult<List<Meal>> result) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        getFragment().startLoading();
        this.result = result;
        String calories =  String.valueOf(DefaultValue.getCalory());//SharedPrefManager.getString(Constants.CALORIES);
        interactor.getMeals(meals, calories, 0, 100, callback);
    }

    public void getCustomMeals(String meals, IResult<List<CustomMeal>> result) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        getFragment().startLoading();
        this.result2 = result;

        interactor.getCustomMeals(meals, String.valueOf(DefaultValue.getCalory()), 0, 100, callback2);
    }

    public void addMeal(int id, final IResult result) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        interactor.addMeal(id, new RequestCallback() {
            @Override
            public void success(Object o) {
                showSuccessMessage(getActivity().getString(R.string.meal_added));
                result.result(null);
            }

            @Override
            public void failed(String msg) {
                showMessage(msg);
                result.result(null);
            }
        });
    }

    private RequestCallback<BaseList<Meal>> callback = new RequestCallback<BaseList<Meal>>() {
        @Override
        public void success(BaseList<Meal> mealBaseList) {
            result.result(mealBaseList.getDataList());
            getFragment().endLoading();
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getFragment().endLoading();
        }
    };

    private RequestCallback<BaseList<CustomMeal>> callback2 = new RequestCallback<BaseList<CustomMeal>>() {
        @Override
        public void success(BaseList<CustomMeal> mealBaseList) {
            result2.result(mealBaseList.getDataList());
            getFragment().endLoading();
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getFragment().endLoading();
        }
    };
}
