package lifestyle.com.lifestyle.interactor;

import lifestyle.com.lifestyle.api.RequestCallback;

public interface IGeneralInteractor {
    void getAbout(RequestCallback<String> resutl);
    void contactUs(String question,RequestCallback resutl);
}
