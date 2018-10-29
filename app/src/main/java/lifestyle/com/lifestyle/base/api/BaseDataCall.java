package lifestyle.com.lifestyle.base.api;

import android.support.annotation.NonNull;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.api.RequestCallback;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.model.User;
import retrofit2.Response;


public abstract class BaseDataCall {

    private final String apiError = "something wrong happened ,please contact developer team";
    private final String serverError = "internal server error ,please contact developer team";
    private final String badConnection = "Check your internet connection";

    @SuppressWarnings("unchecked")
    protected <T> void onDataResponse(@NonNull Response<BaseResponse<T>> response, RequestCallback anInterface) {
        // get response body
        BaseResponse body = response.body();
        if (body == null) {
            // if body == null this mean respond from server total bad
            anInterface.failed(apiError);
            return;
        }
        // check response code from server
        if (response.code() == 200) {
            // check status key from server
            if (body.getStatus()) {
                // i make sure this object which i need
                anInterface.success(body.getData());
            } else {
                // something happened and server tell me what i should do
                anInterface.failed(getMessage(body));
            }
        } else {
            // this case mean response code not equal 200
            anInterface.failed(serverError);
        }
    }

    @SuppressWarnings("unchecked")
    protected void onDataResponseWithoutType(@NonNull Response<BaseResponse> response, RequestCallback anInterface) {
        // get response body
        BaseResponse body = response.body();
        if (body == null) {
            // if body == null this mean respond from server total bad
            anInterface.failed(apiError);
            return;
        }
        // check response code from server
        if (response.code() == 200) {
            // check status key from server
            if (body.getStatus()) {
                // i make sure this object which i need
                anInterface.success(body.getData());
            } else {
                // something happened and server tell me what i should do
                anInterface.failed(getMessage(body));
            }
        } else {
            // this case mean response code not equal 200
            anInterface.failed(serverError);
        }
    }

    @SuppressWarnings("unchecked")
    protected void onDataFailure(Throwable throwable, RequestCallback anInterface) {
        if (throwable instanceof SocketTimeoutException || throwable instanceof SocketException)
            anInterface.failed(badConnection);
        else anInterface.failed(throwable.getMessage());
    }

    private String getMessage(BaseResponse baseResponse) {
         return baseResponse.getMessageAr();
    }
}
