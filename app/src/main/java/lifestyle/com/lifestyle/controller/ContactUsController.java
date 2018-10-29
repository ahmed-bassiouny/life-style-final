package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.interactor.GeneralInteractor;
import lifestyle.com.lifestyle.interactor.IGeneralInteractor;

public class ContactUsController extends BaseController {
    private IGeneralInteractor interactor;
    private IResult result;

    public ContactUsController(Activity activity, Fragment fragment) {
        super(activity, fragment);
        interactor = new GeneralInteractor();
    }

    public void contactUs(String question, IResult result) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        getFragment().startLoading();
        this.result = result;
        interactor.contactUs(question, callback);
    }

    private RequestCallback callback = new RequestCallback() {
        @Override
        public void success(Object o) {
            showSuccessMessage(getActivity().getString(R.string.contact_us_success));
            result.result(null);
            getFragment().endLoading();
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getFragment().endLoading();
        }
    };
}
