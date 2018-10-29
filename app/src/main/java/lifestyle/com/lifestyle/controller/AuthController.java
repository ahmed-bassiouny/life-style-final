package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.interactor.IUserInteractor;
import lifestyle.com.lifestyle.interactor.UserInteractor;
import lifestyle.com.lifestyle.model.User;

public class AuthController extends BaseController {
    private IUserInteractor interactor;

    public AuthController(Activity activity, Fragment fragment) {
        super(activity, fragment);
        interactor = new UserInteractor();
    }

    public void forgetPassword(String email){
        if(networkAvailable()) {
            getFragment().startLoading();
            interactor.forgetPassword(email,callbackForget);
        }else {
            showAlertConnection();
        }
    }

    private RequestCallback callbackForget = new RequestCallback() {
        @Override
        public void success(Object o) {
            showSuccessMessage(getActivity().getString(R.string.check_email));
            getFragment().endLoading();
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getFragment().endLoading();
        }
    };
}

