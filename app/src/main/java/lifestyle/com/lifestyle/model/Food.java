package lifestyle.com.lifestyle.model;

import com.google.gson.annotations.SerializedName;

import lifestyle.com.lifestyle.helper.Utils;

public class Food {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("calories")
    private String calories;

    public int getId() {
        return id;
    }

    public String getName() {
        return Utils.checkString(name);
    }

    public String getCalories() {
        return Utils.checkString(calories);
    }
    public float getCaloriesNumber() {
        try{
            return Float.parseFloat(getCalories());
        }catch (Exception e){
            return 0;
        }
    }
}
