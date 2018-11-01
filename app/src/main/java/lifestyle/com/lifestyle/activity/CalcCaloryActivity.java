package lifestyle.com.lifestyle.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ycuwq.datepicker.date.DatePickerDialogFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.custome_views.MyDatePickerDialogFragment;

public class CalcCaloryActivity extends AppCompatActivity {


    @BindView(R.id.et_birthday)
    TextInputEditText etBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_calory);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.et_birthday)
    public void selectBirthday(){
        MyDatePickerDialogFragment datePickerDialogFragment = new MyDatePickerDialogFragment();
        datePickerDialogFragment.showAnimation(true);
        datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");
        datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
            @Override
            public void onDateChoose(int year, int month, int day) {
                // formate is yyyy-MM-dd
                etBirthday.setText(String.format(Locale.ENGLISH,"%d-%d-%d",year,month,day));
            }
        });
    }
}
