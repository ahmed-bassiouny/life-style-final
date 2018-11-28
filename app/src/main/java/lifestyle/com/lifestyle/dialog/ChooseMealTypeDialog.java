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

public class ChooseMealTypeDialog extends Dialog {

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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }

    @OnClick(R.id.btn_breakfast)
    void breakfast(){
        getContext().startActivity(new Intent(getContext(), CreateMealActivity.class));
    }

}
