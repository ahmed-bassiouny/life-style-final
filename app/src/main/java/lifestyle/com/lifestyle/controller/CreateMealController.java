package lifestyle.com.lifestyle.controller;

import android.app.Activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.interactor.IMealsInteractor;
import lifestyle.com.lifestyle.interactor.MealsInteractor;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.OwnMeal;

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

    public void createMeal(String mealType, List<Food> foods) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        getiActivity().startLoading();
        OwnMeal ownMeal = new OwnMeal();
        ownMeal.setMealType(mealType);
        for (Food item : foods) {
            switch (item.getFoodType()) {
                case Food.CHO:
                    ownMeal.upCho();
                    break;
                case Food.FRUITS:
                    ownMeal.upFruits();
                    break;
                case Food.VEG:
                    ownMeal.upVeg();
                    break;
                case Food.PROTIEN:
                    ownMeal.upProtien();
                    break;
                case Food.MILK:
                    ownMeal.upMilk();
                    break;
                case Food.FAT:
                    ownMeal.upFat();
                    break;
            }
        }
        ownMeal.setUserCalories(SharedPrefManager.getString(Constants.CALORIES));
        interactor.createMeal(ownMeal, new RequestCallback() {
            @Override
            public void success(Object o) {
                getiActivity().endLoading();
                showSuccessMessage("تم تكوين الوجبة بنجاح");
                getActivity().finish();
            }

            @Override
            public void failed(String msg) {
                getiActivity().endLoading();
                showMessage(msg);
            }
        });
    }

    public void getMealsWithType(String type, final IResult<Map<String, List<Food>>> result) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        getiActivity().startLoading();
        interactor.getMealsWithType(type, new RequestCallback<List<Map<String, List<Food>>>>() {
            @Override
            public void success(List<Map<String, List<Food>>> maps) {
                Map<String, List<Food>> newData = new HashMap<>();
                for (Map<String, List<Food>> map : maps) {
                    newData.putAll(map);
                }
                result.result(newData);
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
