package lifestyle.com.lifestyle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMeal {

    @SerializedName("food_quantity")
    @Expose
    private String foodQuantity;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("food_type")
    @Expose
    private String foodType;

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
