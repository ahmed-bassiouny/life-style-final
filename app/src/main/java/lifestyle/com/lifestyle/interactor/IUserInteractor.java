package lifestyle.com.lifestyle.interactor;

import java.io.File;

import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.model.User;

public interface IUserInteractor {

    void register(User user, RequestCallback<User> callback);
    void editProfile(File image, User user, RequestCallback<User> callback);
    void login(String email, String password, RequestCallback<User> callback);
    void forgetPassword(String email, RequestCallback callback);
    void editPassword(String password,String oldPassword, RequestCallback callback);
    void editWeight(String current,String goal,RequestCallback callback);
}
