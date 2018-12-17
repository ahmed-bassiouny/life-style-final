package lifestyle.com.lifestyle.interactor;

import java.util.List;
import java.util.Map;

import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.model.CustomMeal;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.FoodsId;
import lifestyle.com.lifestyle.model.Meal;
import lifestyle.com.lifestyle.model.OwnMeal;
import lifestyle.com.lifestyle.model.UserMeal;

public interface IMealsInteractor {
    void getMeals(String meal,String calories, int offset, int limit, RequestCallback<BaseList<Meal>> callback);
    void getCustomMeals(String meal,String calories, int offset, int limit, RequestCallback<BaseList<CustomMeal>> callback);
    void addMeal(int mealId, RequestCallback callback);
    void getFoods(String type, RequestCallback<BaseList<Food>> callback);
    void createMeal(FoodsId foodsId, RequestCallback callback);
    void createMeal(OwnMeal ownMeal, RequestCallback<List<UserMeal>> callback);
    void getMealsWithType(String type,RequestCallback<List<Map<String,List<Food>>>> callback);

}
