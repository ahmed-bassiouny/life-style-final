package lifestyle.com.lifestyle.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.UserMealAdapter;
import lifestyle.com.lifestyle.model.UserMeal;

public class ShowUserMealDialog extends Dialog {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    public List<UserMeal> lists;
    public String titleStr;

    public ShowUserMealDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show_user_meal);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        ButterKnife.bind(this);
        title.setText(titleStr);
        recycler.setAdapter(new UserMealAdapter(lists));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}
