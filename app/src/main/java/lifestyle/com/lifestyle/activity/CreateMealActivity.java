package lifestyle.com.lifestyle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.MealAdapter;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.dialog.ChooseFoodDialog;
import lifestyle.com.lifestyle.model.OwnMeal;

public class CreateMealActivity extends BaseActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.spinner)
    Spinner spinner;

    private MealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_create_meal);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.create_meal));

        // todo remove it
        List<OwnMeal> v =new ArrayList<>();
    /*    v.add(new OwnMeal("فول"));
        v.add(new OwnMeal("عيش"));
        v.add(new OwnMeal("سلطة"));*/
        adapter = new MealAdapter(v);

        recycler.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ChooseFoodDialog dialog = new ChooseFoodDialog(CreateMealActivity.this);
                dialog.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
