package lifestyle.com.lifestyle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.MealAdapter;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.model.OwnMeal;

public class CreateMealActivity extends BaseActivity {


    @BindView(R.id.recycler_protein)
    RecyclerView recyclerProtein;
    @BindView(R.id.recycler_starches)
    RecyclerView recyclerStarches;
    @BindView(R.id.recycler_vegetables)
    RecyclerView recyclerVegetables;
    @BindView(R.id.recycler_fruits)
    RecyclerView recyclerFruits;
    @BindView(R.id.recycler_milks)
    RecyclerView recyclerMilks;

    private MealAdapter proteinAdapter, starchesAdapter, vegetablesAdapter, fruitsAdapter, milkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_create_meal);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.create_meal));

        // todo remove it
        List<OwnMeal> v =new ArrayList<>();
        v.add(new OwnMeal("فول", "https://www.ma7shy.com/media/cache/image_recipe_large/uploads/media/%D9%81%D9%88%D9%84_1434925978.jpg"));
        v.add(new OwnMeal("عدس", "https://www.ma7shy.com/media/cache/image_recipe_large/uploads/media/slow-cooker-yellow-lentil-dahl_eh3bnr_1462917176.jpg"));
        proteinAdapter = new MealAdapter(v);
        starchesAdapter = vegetablesAdapter = fruitsAdapter = milkAdapter = proteinAdapter;
        // todo end
        recyclerFruits.setAdapter(fruitsAdapter);
        recyclerMilks.setAdapter(milkAdapter);
        recyclerVegetables.setAdapter(vegetablesAdapter);
        recyclerStarches.setAdapter(starchesAdapter);
        recyclerProtein.setAdapter(proteinAdapter);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
