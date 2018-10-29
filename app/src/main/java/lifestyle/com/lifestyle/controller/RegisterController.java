
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

public class RegisterController extends BaseController{
    private IUserInteractor interactor;

    public RegisterController(Activity activity, Fragment fragment) {
        super(activity, fragment);
        interactor = new UserInteractor();
    }

    public void register(String name,String email,String height,
                         String weight,String password,boolean isMale){
        if(networkAvailable()) {
            getFragment().startLoading();
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setHeight(height);
            user.setCurrentWeight(weight);
            user.setPassword(password);
            if (isMale)
                user.setMale();
            else
                user.setFemale();
            interactor.register(user, callback);
        }else {
            showAlertConnection();
        }
    }
    RequestCallback<User> callback = new RequestCallback<User>() {
        @Override
        public void success(User user) {
            SharedPrefManager.setObject(Constants.USER,user);
            //launchActivityWithFinish(HomeActivity.class);
            getFragment().endLoading();
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getFragment().endLoading();
        }
    };
}
