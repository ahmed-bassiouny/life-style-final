package lifestyle.com.lifestyle.model;

import com.google.gson.annotations.SerializedName;

import lifestyle.com.lifestyle.helper.Utils;

public class Food {

    public final static int CHO = 1 ;
    public final static int FRUITS = 3 ;
    public final static int VEG = 4 ;
    public final static int PROTIEN = 5 ;
    public final static int MILK = 6 ;
    public final static int FAT = 8;

    @SerializedName("name")
    private String name;
    @SerializedName("calories")
    private String calories;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("food_type_id")
    private int foodType;
    private String foodTypeString;
    public Food() {
    }

    public Food(String name, String foodTypeString) {
        this.name = name;
        this.foodTypeString = foodTypeString;
    }

    public String getQuantity() {
        return Utils.checkString(quantity);
    }

    public int getFoodType() {
        return foodType;
    }

    public String getFoodTypeString() {
        return Utils.checkString(foodTypeString);
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
