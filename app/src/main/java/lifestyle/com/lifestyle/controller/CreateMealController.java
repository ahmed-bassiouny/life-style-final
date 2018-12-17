package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.dialog.ShowUserMealDialog;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.helper.DefaultValue;
import lifestyle.com.lifestyle.interactor.IMealsInteractor;
import lifestyle.com.lifestyle.interactor.MealsInteractor;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.OwnMeal;
import lifestyle.com.lifestyle.model.UserMeal;

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

    public void createMeal(final String mealType, List<Food> foods) {
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
                    ownMeal.setCho(item.getId());
                    break;
                case Food.FRUITS:
                    ownMeal.setFruits(item.getId());
                    break;
                case Food.VEG:
                    ownMeal.setVeg(item.getId());
                    break;
                case Food.PROTIEN:
                    ownMeal.setProtien(item.getId());
                    break;
                case Food.MILK:
                    ownMeal.setMilk(item.getId());
                    break;
                case Food.FAT:
                    ownMeal.setFat(item.getId());
                    break;
            }
        }
        ownMeal.setUserCalories(String.valueOf(DefaultValue.getCalory()));
        interactor.createMeal(ownMeal, new RequestCallback<List<UserMeal>>() {
            @Override
            public void success(List<UserMeal> userMeals) {
                getiActivity().endLoading();
                showSuccessMessage("تم تكوين الوجبة بنجاح");
                ShowUserMealDialog dialog = new ShowUserMealDialog(getActivity());
                dialog.lists = userMeals;
                dialog.titleStr = getTitleMeals(mealType);
                dialog.show();

            }

            @Override
            public void failed(String msg) {
                getiActivity().endLoading();
                showMessage(msg);
            }
        });
//        interactor.createMeal(ownMeal, new RequestCallback() {
//            @Override
//            public void success(Object o) {
//                getiActivity().endLoading();
//                showSuccessMessage("تم تكوين الوجبة بنجاح");
//                getActivity().finish();
//            }
//
//            @Override
//            public void failed(String msg) {
//
//            }
//        });
    }

    private String getTitleMeals(String mealType) {
        if(mealType.equals(OwnMeal.BREAKFAST))
            return "وجبة الافطار";
        else if(mealType.equals(OwnMeal.LUNCH))
            return "وجبة الغداء";
        else if(mealType.equals(OwnMeal.DINNER))
            return "وجبة العشاء";
        else return "";
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
