package lifestyle.com.lifestyle.model;

import com.google.gson.annotations.SerializedName;

import lifestyle.com.lifestyle.helper.Utils;

public class Food {

    @SerializedName("name")
    private String name;
    @SerializedName("calories")
    private String calories;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("food_type")
    private String foodType;

    public String getQuantity() {
        return Utils.checkString(quantity);
    }

    public String getFoodType() {
        return Utils.checkString(foodType);
    }

    public String getName() {
        return Utils.checkString(name);
    }

    public String getCalories() {
        return Utils.checkString(calories);
    }

    public float getCaloriesNumber() {
        try {
            return Float.parseFloat(getCalories());
        } catch (Exception e) {
            return 0;
        }
    }
}
