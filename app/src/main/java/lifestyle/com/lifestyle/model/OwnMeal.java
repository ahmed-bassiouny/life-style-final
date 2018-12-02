package lifestyle.com.lifestyle.model;

import com.google.gson.annotations.SerializedName;

public class OwnMeal {

    public final static String BREAKFAST = "breakfast";
    public final static String LUNCH = "lunch";
    public final static String DINNER = "dinner";

    private String mealType;
    @SerializedName("meal_type_id")
    private int mealTypeId;
    @SerializedName("user_calories")
    private String userCalories;
    private int cho;
    private int protien;
    private int veg;
    private int fruits;
    private int milk;
    private int fat;

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
        if (mealType.equals(BREAKFAST))
            mealTypeId = 1;
        else if (mealType.equals(LUNCH))
            mealTypeId = 2;
        else if (mealType.equals(DINNER))
            mealTypeId = 3;
    }

    public String getUserCalories() {
        return userCalories;
    }

    public void setUserCalories(String userCalories) {
        this.userCalories = userCalories;
    }

    public int getCho() {
        return cho;
    }

    public void setCho(int cho) {
        this.cho = cho;
    }

    public void upCho() {
        this.cho++;
    }

    public int getProtien() {
        return protien;
    }

    public void setProtien(int protien) {
        this.protien = protien;
    }

    public void upProtien() {
        this.protien++;
    }

    public int getVeg() {
        return veg;
    }

    public void setVeg(int veg) {
        this.veg = veg;
    }

    public void upVeg() {
        this.veg++;
    }

    public int getFruits() {
        return fruits;
    }

    public void setFruits(int fruits) {
        this.fruits = fruits;
    }

    public void upFruits() {
        this.fruits++;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public void upMilk() {
        this.milk++;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void upFat() {
        this.fat++;
    }
}
