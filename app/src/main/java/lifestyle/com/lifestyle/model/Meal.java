package lifestyle.com.lifestyle.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lifestyle.com.lifestyle.helper.Utils;

public class Meal {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("foods")
    private List<String> foods;

    public int getId() {
        return id;
    }

    public String getName() {
        return Utils.checkString(name);
    }

    public List<String> getFoods() {
        return foods;
    }
}
