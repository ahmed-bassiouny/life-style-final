package lifestyle.com.lifestyle.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lifestyle.com.lifestyle.helper.Utils;

public class Meal {

    @SerializedName("meal_id")
    private int id;
    @SerializedName("calories_count")
    private String caloriesCount;
    @SerializedName("foods")
    private List<Food> foods;

    public int getId() {
        return id;
    }

    public String getCaloriesCount() {
        return Utils.checkString(caloriesCount);
    }

    public List<Food> getFoods() {
        return foods;
    }
}
