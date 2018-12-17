package lifestyle.com.lifestyle.interactor;

import java.util.List;
import java.util.Map;

import lifestyle.com.lifestyle.api.DataCall;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.model.CustomMeal;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.FoodsId;
import lifestyle.com.lifestyle.model.Meal;
import lifestyle.com.lifestyle.model.OwnMeal;

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
    public void getCustomMeals(String meal, String calories, int offset, int limit, RequestCallback<BaseList<CustomMeal>> callback) {
        interactor.getCustomMeals(meal, calories, offset, limit, callback);
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
    public void createMeal(OwnMeal ownMeal, RequestCallback callback) {
        interactor.createMeal(ownMeal, callback);
    }

    @Override
    public void getMealsWithType(String type,String ca, RequestCallback<List<Map<String, List<Food>>>> callback) {
        interactor.getMealsWithType(type,ca, callback);
    }

    @Override
    public void addMeal(int mealId, RequestCallback callback) {
        interactor.addMeal(mealId,callback);
    }
}
