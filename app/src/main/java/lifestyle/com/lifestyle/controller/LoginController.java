package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.interactor.IUserInteractor;
import lifestyle.com.lifestyle.interactor.UserInteractor;
import lifestyle.com.lifestyle.model.User;

public class LoginController extends BaseController {
    private IUserInteractor interactor;

    public LoginController(Activity activity, Fragment fragment) {
        super(activity, fragment);
        interactor = new UserInteractor();
    }

    public void login(String email, String password) {
        if (networkAvailable()) {
            getFragment().startLoading();
            interactor.login(email, password,callback);
        } else {
            showAlertConnection();
        }
    }

    private RequestCallback<User> callback = new RequestCallback<User>() {
        @Override
        public void success(User user) {
            SharedPrefManager.setObject(Constants.USER, user);
            //launchActivityWithFinish(MainActivity.class);
            getFragment().endLoading();
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getFragment().endLoading();
        }

        ;
    };
}
