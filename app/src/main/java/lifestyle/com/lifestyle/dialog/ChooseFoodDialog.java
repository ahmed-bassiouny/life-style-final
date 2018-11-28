package lifestyle.com.lifestyle.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;

public class ChooseFoodDialog extends Dialog {

    @BindView(R.id.list)
    ListView list;

    public ChooseFoodDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_food);
        ButterKnife.bind(this);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        String[] array = {"فول","عدس","شوفان","الحمص"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
        list.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}
