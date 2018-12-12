
package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.activity.HomeActivity;
import lifestyle.com.lifestyle.alarms.Alarm;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.helper.DefaultValue;
import lifestyle.com.lifestyle.helper.Equation;
import lifestyle.com.lifestyle.interactor.IUserInteractor;
import lifestyle.com.lifestyle.interactor.UserInteractor;
import lifestyle.com.lifestyle.model.User;

public class RegisterController extends BaseController {
    private IUserInteractor interactor;

    public RegisterController(Activity activity) {
        super(activity);
        interactor = new UserInteractor();
    }

    public void editProfile(User user) {
        if (networkAvailable()) {
            getiActivity().startLoading();
            interactor.editProfile(user, callbackForEdit);
        } else {
            showAlertConnection();
        }
    }

    public void register(User user) {
        if (networkAvailable()) {
            getiActivity().startLoading();
            interactor.editProfile(user, callbackForRegister);
        } else {
            showAlertConnection();
        }
    }

    RequestCallback<User> callbackForEdit = new RequestCallback<User>() {
        @Override
        public void success(User user) {
            SharedPrefManager.setObject(Constants.USER, user);
            SharedPrefManager.setString(Constants.CALORIES, Equation.calculateCalory(user));
            showErrorMessage(getActivity().getString(R.string.saved));
            getiActivity().endLoading();
            finishctivity();
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getiActivity().endLoading();
        }
    };

    RequestCallback<User> callbackForRegister = new RequestCallback<User>() {
        @Override
        public void success(User user) {
            SharedPrefManager.setObject(Constants.USER, user);
            SharedPrefManager.setString(Constants.CALORIES, Equation.calculateCalory(user));
            SharedPrefManager.setString(Constants.CURRENT_CALORY, Equation.calculateCalory(user));
            showErrorMessage(getActivity().getString(R.string.saved));
            getiActivity().endLoading();
            launchActivityWithFinishAndClearStack(HomeActivity.class);
            // set default value
            DefaultValue.waterAlarm(getActivity());
            Alarm.setAlarmForMeal(getActivity());
            Alarm.resetCalory(getActivity());
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getiActivity().endLoading();
        }
    };


}
