package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.io.File;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.base.ui.BaseController;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.interactor.IUserInteractor;
import lifestyle.com.lifestyle.interactor.UserInteractor;
import lifestyle.com.lifestyle.model.User;

public class ProfileController extends BaseController {

    private IUserInteractor interactor;
    private IResult result;

    public ProfileController(Activity activity, Fragment fragment) {
        super(activity, fragment);
        interactor = new UserInteractor();
    }

    public void updateUser(File image, String name, String phone,
                           String birthday, String weight, String height,
                           int workType, int goal, boolean isMale,
                           String wakeupTime, String sleepTime, boolean sleepTimesStatic,
                           IResult result) {
        if (!networkAvailable()) {
            showAlertConnection();
            return;
        }
        this.result = result;
        getFragment().startLoading();
        User user = SharedPrefManager.getObject(Constants.USER, User.class);
        user.setName(name);
        user.setPhone(phone);
        user.setBirthday(birthday);
        user.setCurrentWeight(weight);
        user.setHeight(height);
        user.setWorkType(String.valueOf(workType));
        user.setPurpose(String.valueOf(goal));
        if (isMale) user.setMale();
        else user.setFemale();
        if (sleepTimesStatic) {
            user.setWakesUpAt(wakeupTime);
            user.setSleepingAt(sleepTime);
        } else {
            user.setWakesUpAt("");
            user.setSleepingAt("");
        }
        interactor.editProfile(image,user, callback);
    }

    private RequestCallback<User> callback = new RequestCallback<User>() {
        @Override
        public void success(User user) {
            showSuccessMessage(getActivity().getString(R.string.profile_updated));
            SharedPrefManager.setObject(Constants.USER, user);
            getFragment().endLoading();
            result.result(null);
        }

        @Override
        public void failed(String msg) {
            showErrorMessage(msg);
            getFragment().endLoading();
        }

        ;
    };
}
