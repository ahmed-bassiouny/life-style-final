package lifestyle.com.lifestyle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodsId {

    @SerializedName("food_ids")
    @Expose
    private List<Integer> id;

    public FoodsId() {
    }

    public FoodsId(List<Integer> id) {
        this.id = id;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }
}
