package lifestyle.com.lifestyle.interactor;

import java.io.File;

import lifestyle.com.lifestyle.api.DataCall;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.model.User;

public class UserInteractor implements IUserInteractor {

    private IUserInteractor interactor;

    public UserInteractor() {
        this.interactor = new DataCall();
    }

    @Override
    public void register(User user, RequestCallback<User> callback) {
        interactor.register(user, callback);
    }

    @Override
    public void editProfile(File image, User user, RequestCallback<User> callback) {
        interactor.editProfile(image,user, callback);
    }

    @Override
    public void login(String email, String password, RequestCallback<User> callback) {
        interactor.login(email, password, callback);
    }

    @Override
    public void forgetPassword(String email, RequestCallback callback) {
        interactor.forgetPassword(email, callback);
    }

    @Override
    public void editPassword(String password, String oldPassword, RequestCallback callback) {
        interactor.editPassword(password, oldPassword, callback);
    }

    @Override
    public void editWeight(String current, String goal, RequestCallback callback) {
        interactor.editWeight(current,goal,callback);
    }
}
