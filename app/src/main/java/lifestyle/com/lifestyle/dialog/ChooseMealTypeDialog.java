package lifestyle.com.lifestyle.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.activity.CreateMealActivity;
import lifestyle.com.lifestyle.model.OwnMeal;

public class ChooseMealTypeDialog extends Dialog {

    private Intent intent;

    public ChooseMealTypeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_meal_type);
        ButterKnife.bind(this);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        intent = new Intent(getContext(), CreateMealActivity.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }

    @OnClick(R.id.btn_breakfast)
    void breakfast(){
        intent.putExtra("data",OwnMeal.BREAKFAST);
        getContext().startActivity(intent);
        dismiss();
    }

    @OnClick(R.id.btn_lunch)
    void lunch(){
        intent.putExtra("data",OwnMeal.LUNCH);
        getContext().startActivity(intent);
        dismiss();
    }

    @OnClick(R.id.btn_dinner)
    void dinner(){
        intent.putExtra("data",OwnMeal.DINNER);
        getContext().startActivity(intent);
        dismiss();
    }

}
