package lifestyle.com.lifestyle.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.MealAdapter;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.controller.CreateMealController;
import lifestyle.com.lifestyle.dialog.ChooseFoodDialog;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.OwnMeal;

public class CreateMealActivity extends BaseActivity implements BaseController.IResult<Map<String, List<Food>>> {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.pbProgress)
    ProgressBar pbProgress;
    @BindView(R.id.btn_save_meal)
    Button btnSave;
    private MealAdapter adapter;
    private CreateMealController controller;
    private Map<String, List<Food>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_create_meal);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.create_meal));
        controller = new CreateMealController(this);
        spinner.setEnabled(false);
        // todo remove it
        List<OwnMeal> v = new ArrayList<>();
    /*    v.add(new OwnMeal("فول"));
        v.add(new OwnMeal("عيش"));
        v.add(new OwnMeal("سلطة"));*/
        adapter = new MealAdapter(v);

        recycler.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    Toast.makeText(CreateMealActivity.this,spinner.getSelectedItem().toString() , Toast.LENGTH_SHORT).show();
                }
                ChooseFoodDialog dialog = new ChooseFoodDialog(CreateMealActivity.this);
                dialog.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        controller.getMealsWithType("", this);
    }

    @Override
    public void startLoading() {
        pbProgress.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
    }

    @Override
    public void endLoading() {
        pbProgress.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
    }

    @Override
    public void result(Map<String, List<Food>> stringListMap) {
        this.map = stringListMap;
        spinner.setEnabled(true);
        List<String> list = new ArrayList<>();
        list.add("اختر طعامك");
        list.addAll(stringListMap.keySet());
        spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, list));
        spinner.setSelection(0);
    }
}
