package lifestyle.com.lifestyle.base.ui;

import android.view.View;

public interface IBaseController {
    void showMessage(String msg);
    void showAlertConnection();
    void showAlertConnectionWithAction(String msg,View.OnClickListener listener);
    void showSuccessMessage(String msg);
    void showErrorMessage(String msg);
}
