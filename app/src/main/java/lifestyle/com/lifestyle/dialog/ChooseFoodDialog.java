package lifestyle.com.lifestyle.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.FoodNameAdapter;
import lifestyle.com.lifestyle.base.ui.IAdapter;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.FromDialogToActivity;

public class ChooseFoodDialog extends Dialog implements IAdapter<Food> {

    @BindView(R.id.list)
    RecyclerView list;

    public List<Food> foods;
    public FromDialogToActivity comminicator;
    public ChooseFoodDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_food);
        ButterKnife.bind(this);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        list.setAdapter(new FoodNameAdapter(this,foods));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }

    @Override
    public void onClick(Food food, int position) {
        comminicator.getFood(food);
        dismiss();
    }
}
