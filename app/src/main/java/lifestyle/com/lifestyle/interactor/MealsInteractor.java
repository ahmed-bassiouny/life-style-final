package lifestyle.com.lifestyle.interactor;

import java.util.List;
import java.util.Map;

import lifestyle.com.lifestyle.api.DataCall;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.FoodsId;
import lifestyle.com.lifestyle.model.Meal;

public class MealsInteractor implements IMealsInteractor {

    private IMealsInteractor interactor;

    public MealsInteractor() {
        interactor = new DataCall();
    }

    @Override
    public void getMeals(String meal, String calories,int offset, int limit, RequestCallback<BaseList<Meal>> callback) {
     interactor.getMeals(meal,calories,offset,limit,callback);
    }

    @Override
    public void getFoods(String type, RequestCallback<BaseList<Food>> callback) {
        interactor.getFoods(type,callback);
    }

    @Override
    public void createMeal(FoodsId foodsId, RequestCallback callback) {
        interactor.createMeal(foodsId,callback);
    }

    @Override
    public void getMealsWithType(String type, RequestCallback<Map<String, List<String>>> callback) {
        interactor.getMealsWithType(type, callback);
    }

    @Override
    public void addMeal(int mealId, RequestCallback callback) {
        interactor.addMeal(mealId,callback);
    }
}
