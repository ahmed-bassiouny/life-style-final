package lifestyle.com.lifestyle.controller;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.activity.CalcCaloryActivity;
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

public class LoginController extends BaseController {

    private IUserInteractor interactor;


    public LoginController(Activity activity, Fragment fragment) {
        super(activity, fragment);
        interactor = new UserInteractor();
    }

    public void login(String email) {
        if (networkAvailable()) {
            getFragment().startLoading();
            interactor.loginSocial(email, callback);
        } else {
            showAlertConnection();
            getFragment().endLoading();
        }
    }

    private RequestCallback<User> callback = new RequestCallback<User>() {
        @Override
        public void success(User user) {
            SharedPrefManager.setObject(Constants.USER, user);
            getFragment().endLoading();
            if (user.getBirthday().isEmpty()) {
                // open calc calory
                launchActivityWithFinish(CalcCaloryActivity.class);
            } else {
                SharedPrefManager.setString(Constants.CALORIES, calculateCalory(user));
                // set default value
                DefaultValue.waterAlarm(getActivity());
                Alarm.setAlarmForMeal(getActivity());
                launchActivityWithFinish(HomeActivity.class);
            }
        }

        @Override
        public void failed(String msg) {
            showMessage(msg);
            getFragment().endLoading();
        }
    };

    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            SharedPrefManager.setString(Constants.USER_NAME,account.getDisplayName());
        /*    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if(user == null) {
                                    login(user.getEmail());
                                }else {
                                    showErrorMessage("Please contact developer team");
                                }
                            } else {
                                showErrorMessage(task.getException().getMessage());
                            }
                        }
                    });*/
            login(account.getEmail());
        } catch (ApiException e) {
            showErrorMessage("من فضلك جرب مرة اخرى");
            getFragment().endLoading();
        }
    }

    private String calculateCalory(User user) {
        double bmrResut = Equation.calBMR(Integer.parseInt(user.getCurrentWeight()),
                Integer.parseInt(user.getHeight()), user.getBirthday(), user.isMale());
        int tee = (int) Equation.getTEE(bmrResut);
        return String.valueOf(tee);
    }
}
