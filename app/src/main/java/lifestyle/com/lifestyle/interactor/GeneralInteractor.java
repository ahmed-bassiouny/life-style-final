package lifestyle.com.lifestyle.interactor;

import lifestyle.com.lifestyle.api.DataCall;
import lifestyle.com.lifestyle.api.RequestCallback;

public class GeneralInteractor implements IGeneralInteractor{

    private IGeneralInteractor interactor;

    public GeneralInteractor() {
        interactor = new DataCall();
    }

    @Override
    public void getAbout(RequestCallback<String> resutl) {
        interactor.getAbout(resutl);
    }

    @Override
    public void contactUs(String question, RequestCallback resutl) {
        interactor.contactUs(question,resutl);
    }
}
