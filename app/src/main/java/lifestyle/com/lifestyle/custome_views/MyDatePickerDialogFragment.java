package lifestyle.com.lifestyle.custome_views;

import com.ycuwq.datepicker.date.DatePickerDialogFragment;

import lifestyle.com.lifestyle.R;

public class MyDatePickerDialogFragment extends DatePickerDialogFragment {

    @Override
    protected void initChild() {
        super.initChild();
        mCancelButton.setText(getString(R.string.cancel));
        mDecideButton.setText(getString(R.string.agree));
    }
}
