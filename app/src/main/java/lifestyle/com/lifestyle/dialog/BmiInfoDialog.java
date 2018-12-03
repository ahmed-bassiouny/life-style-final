package lifestyle.com.lifestyle.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import lifestyle.com.lifestyle.R;

public class BmiInfoDialog extends Dialog {
    public BmiInfoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bmi_info);
    }
}
