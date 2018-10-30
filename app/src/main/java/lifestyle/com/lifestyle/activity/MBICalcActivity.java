package lifestyle.com.lifestyle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.adapter.AgeAdapter;
import lifestyle.com.lifestyle.adapter.HeightAdapter;
import lifestyle.com.lifestyle.base.ui.BaseActivity;
import lifestyle.com.lifestyle.custome_views.CircleProgressBar;

public class MBICalcActivity extends BaseActivity {

    @BindView(R.id.custom_progressBar)
    CircleProgressBar progressBar;
    @BindView(R.id.text_progress)
    TextView text_progress;
    int pro = 1;
    @BindView(R.id.recycler_age)
    RecyclerView recyclerAge;
    @BindView(R.id.recycler_height)
    RecyclerView recyclerHeight;
    @BindView(R.id.recycler_weight)
    RecyclerView recyclerWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_mbicalc);
        ButterKnife.bind(this);
        initToolbar(getString(R.string.bmi));
        recyclerAge.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerAge.setAdapter(new AgeAdapter(this,getAges()));

        recyclerHeight.setLayoutManager(new LinearLayoutManager(this));
        recyclerHeight.setAdapter(new HeightAdapter(this,getHeight()));

        recyclerWeight.setLayoutManager(new LinearLayoutManager(this));
        recyclerWeight.setAdapter(new HeightAdapter(this,getWeight()));
        updateProgress(30);


    }

    private void updateProgress(final float newVal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pro < newVal) {
                    try {
                        Thread.sleep(200);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(pro);
                                text_progress.setText(String.valueOf(pro));
                            }
                        });
                        pro += 1;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private List<Integer> getAges() {
        List<Integer> results = new ArrayList<>();
        for (int i = 10; i < 60; i++)
            results.add(i);
        return results;
    }
    private List<Integer> getHeight() {
        List<Integer> results = new ArrayList<>();
        for (int i = 140; i < 250; i++)
            results.add(i);
        return results;
    }

    private List<Integer> getWeight() {
        List<Integer> results = new ArrayList<>();
        for (int i = 40; i < 200; i++)
            results.add(i);
        return results;
    }
}
