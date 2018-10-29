package lifestyle.com.lifestyle.base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;



public abstract class BaseFragment extends Fragment implements IFragment {

    public Context mContext;
    private boolean loading = false; // this attribute show if app make request or not

    protected void setTitle(@StringRes int title) {
        if (getActivity() != null)
            getActivity().setTitle(getString(title));
    }

    protected void setTitle(String title) {
        if (getActivity() != null)
            getActivity().setTitle(title);
    }

    public void pressBack() {
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    public AppCompatActivity getMyActivity() {
        return (AppCompatActivity) mContext;
    }

    public final void startRequest() {
        loading = true;
    }

    public final void endRequest() {
        loading = false;
    }

    public final boolean getIsLoading() {
        return loading;
    }

    public void hideKeyboard() {
        Activity activity = (Activity) mContext;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
