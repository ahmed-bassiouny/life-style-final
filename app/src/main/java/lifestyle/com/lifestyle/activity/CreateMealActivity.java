package lifestyle.com.lifestyle.activity;

import android.content.DialogInterface;
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
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.MealAdapter;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.base.ui.IAdapter;
import lifestyle.com.lifestyle.controller.CreateMealController;
import lifestyle.com.lifestyle.dialog.ChooseFoodDialog;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.FromDialogToActivity;
import lifestyle.com.lifestyle.model.OwnMeal;

public class CreateMealActivity extends BaseActivity implements BaseController.IResult<Map<String, List<Food>>>,FromDialogToActivity {


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
    private String mealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_create_meal);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.create_meal));
        controller = new CreateMealController(this);
        spinner.setEnabled(false);
        adapter = new MealAdapter();

        recycler.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    ChooseFoodDialog dialog = new ChooseFoodDialog(CreateMealActivity.this);
                    dialog.foods = map.get(spinner.getSelectedItem().toString());
                    dialog.comminicator = CreateMealActivity.this;
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            spinner.setSelection(0);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mealType = getIntent().getStringExtra("data");
        controller.getMealsWithType(mealType, this);
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

    @Override
    public void getFood(Food food) {
        adapter.addFood(food);
    }

    @OnClick(R.id.btn_save_meal)
    public void save(){
        if(adapter.getItemCount() == 0){
            controller.showErrorMessage("من فضلك قم باختيار طعام لتكوين وجبتك");
        }else {
            controller.createMeal(mealType,adapter.getList());
        }
    }
}
