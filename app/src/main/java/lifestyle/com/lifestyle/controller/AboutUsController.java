package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.interactor.GeneralInteractor;
import lifestyle.com.lifestyle.interactor.IGeneralInteractor;

public class AboutUsController extends BaseController {

    private IGeneralInteractor interactor;
    private IResult<String> result;

    public AboutUsController(Activity activity, Fragment fragment) {
        super(activity, fragment);
        interactor = new GeneralInteractor();
    }

    public void getAbout(IResult<String> result) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        getFragment().startLoading();
        this.result = result;
        interactor.getAbout(callback);
    }

    private RequestCallback<String> callback = new RequestCallback<String>() {
        @Override
        public void success(String s) {
            getFragment().endLoading();
            result.result(s);
        }

        @Override
        public void failed(String msg) {
            getFragment().endLoading();
            showErrorMessage(msg);
        }
    };
}
