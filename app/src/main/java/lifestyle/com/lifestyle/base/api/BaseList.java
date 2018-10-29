package lifestyle.com.lifestyle.base.api;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class BaseList<T> {

    @SerializedName("count")
    @Expose
    private int itemsCount;
    @SerializedName("list")
    private List<T> dataList;

    public int getItemsCount() {
        return itemsCount;
    }

    public List<T> getDataList() {
        if(dataList == null)
            dataList = new ArrayList<>();
        return dataList;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
