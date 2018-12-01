package lifestyle.com.lifestyle.controller;

import android.app.Activity;

import java.util.List;
import java.util.Map;

import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.interactor.IMealsInteractor;
import lifestyle.com.lifestyle.interactor.MealsInteractor;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.FoodsId;

public class CreateMealController extends BaseController {

    private IMealsInteractor interactor;

    public CreateMealController(Activity activity) {
        super(activity);
        interactor = new MealsInteractor();
    }

    public void getFood(String type, final IResult<List<Food>> result) {
        if (!networkAvailable()) {
            showAlertConnection();
            result.result(null);
            return;
        }
        interactor.getFoods(type, new RequestCallback<BaseList<Food>>() {
            @Override
            public void success(BaseList<Food> foodBaseList) {
                result.result(foodBaseList.getDataList());
            }

            @Override
            public void failed(String msg) {
                showMessage(msg);
                result.result(null);
            }
        });
    }

    public void createMeal(FoodsId foodsId, final IResult<Boolean> result) {
        if (!networkAvailable()) {
            showAlertConnection();
            result.result(false);
            return;
        }
        interactor.createMeal(foodsId, new RequestCallback() {
            @Override
            public void success(Object o) {
                showSuccessMessage(getActivity().getString(R.string.meal_created));
                result.result(true);
            }

            @Override
            public void failed(String msg) {
                showMessage(msg);
                result.result(false);
            }
        });
    }

    public void getMealsWithType(String type, final IResult<Map<String, List<String>>> result) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        getiActivity().startLoading();
        interactor.getMealsWithType(type, new RequestCallback<Map<String, List<String>>>() {
            @Override
            public void success(Map<String, List<String>> stringListMap) {
                result.result(stringListMap);
                getiActivity().endLoading();
            }

            @Override
            public void failed(String msg) {
                showErrorMessage(msg);
                getiActivity().endLoading();
            }
        });
    }
}
