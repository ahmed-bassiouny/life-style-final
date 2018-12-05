package lifestyle.com.lifestyle.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lifestyle.com.lifestyle.base.api.BaseDataCall;
import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.base.api.BaseResponse;
import lifestyle.com.lifestyle.helper.Utils;
import lifestyle.com.lifestyle.interactor.IGeneralInteractor;
import lifestyle.com.lifestyle.interactor.IMealsInteractor;
import lifestyle.com.lifestyle.interactor.IUserInteractor;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.FoodsId;
import lifestyle.com.lifestyle.model.Meal;
import lifestyle.com.lifestyle.model.OwnMeal;
import lifestyle.com.lifestyle.model.User;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCall extends BaseDataCall implements IUserInteractor, IMealsInteractor, IGeneralInteractor {

    @Override
    public void register(User user, final RequestCallback<User> callback) {
        Call<BaseResponse<User>> responseCall = RetrofitConfig.httpApiInterface.register(user);
        responseCall.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                onDataResponse(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void editProfile(File image, User user, final RequestCallback<User> callback) {
        Call<BaseResponse<User>> responseCall = RetrofitConfig.httpApiInterface.editProfile(
                Utils.convertFileToPart(image),
                Utils.convertStringToPart(user.getName()),
                Utils.convertStringToPart(user.getEmail()),
                Utils.convertStringToPart(user.getPhone()),
                Utils.convertStringToPart(user.getCountry()),
                Utils.convertStringToPart(user.getCity()),
                Utils.convertStringToPart(user.getCurrentWeight()),
                Utils.convertStringToPart(user.getHeight()),
                Utils.convertStringToPart(user.getGender()),
                Utils.convertStringToPart(user.getWorkType()),
                Utils.convertStringToPart(user.getPurpose()),
                Utils.convertStringToPart(user.getSleepingType()),
                Utils.convertStringToPart(user.getSleepingAt()),
                Utils.convertStringToPart(user.getWakesUpAt()),
                Utils.convertStringToPart(String.valueOf(1)),
                Utils.convertStringToPart(user.getBirthday())
        );
        responseCall.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                onDataResponse(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void editProfile(User user, final RequestCallback<User> callback) {
        Call<BaseResponse<User>> responseCall = RetrofitConfig.httpApiInterface.editProfile(user);
        responseCall.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                onDataResponse(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void login(String email, String password, final RequestCallback<User> callback) {
        Call<BaseResponse<User>> responseCall = RetrofitConfig.httpApiInterface.login(email, password);
        responseCall.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                onDataResponse(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void loginSocial(String email, final RequestCallback<User> callback) {
        Call<BaseResponse<User>> responseCall = RetrofitConfig.httpApiInterface.loginSocial(email);
        responseCall.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                onDataResponse(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void forgetPassword(String email, final RequestCallback callback) {
        Call<BaseResponse> responseCall = RetrofitConfig.httpApiInterface.forgetPassword(email);
        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                onDataResponseWithoutType(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });

    }

    @Override
    public void editPassword(String password, String oldPassword, final RequestCallback callback) {
        Call<BaseResponse> responseCall = RetrofitConfig.httpApiInterface.editPassword(password, oldPassword);
        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                onDataResponseWithoutType(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void editWeight(String current, String goal, final RequestCallback callback) {
        Call<BaseResponse> responseCall = RetrofitConfig.httpApiInterface.editWeight(current, goal);
        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                onDataResponseWithoutType(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void getMeals(String meal, String calories, int offset, int limit, final RequestCallback<BaseList<Meal>> callback) {
        Call<BaseResponse<BaseList<Meal>>> responseCall = RetrofitConfig.httpApiInterface.getMeals(meal, calories, offset, limit);
        responseCall.enqueue(new Callback<BaseResponse<BaseList<Meal>>>() {
            @Override
            public void onResponse(Call<BaseResponse<BaseList<Meal>>> call, Response<BaseResponse<BaseList<Meal>>> response) {
                onDataResponse(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse<BaseList<Meal>>> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void getFoods(String type, final RequestCallback<BaseList<Food>> callback) {
        Call<BaseResponse<BaseList<Food>>> responseCall = RetrofitConfig.httpApiInterface.getFoods(type);
        responseCall.enqueue(new Callback<BaseResponse<BaseList<Food>>>() {
            @Override
            public void onResponse(Call<BaseResponse<BaseList<Food>>> call, Response<BaseResponse<BaseList<Food>>> response) {
                onDataResponse(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse<BaseList<Food>>> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void createMeal(FoodsId foodsId, final RequestCallback callback) {
        Call<BaseResponse> responseCall = RetrofitConfig.httpApiInterface.createMeal(foodsId);
        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                onDataResponseWithoutType(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void createMeal(OwnMeal ownMeal, final RequestCallback callback) {
        Call<BaseResponse> responseCall = RetrofitConfig.httpApiInterface.createMeal(ownMeal);
        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                onDataResponseWithoutType(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void getMealsWithType(String type,final RequestCallback<List<Map<String,List<Food>>>> callback) {
        Call<BaseResponse<List<Map<String,List<Food>>>>> responseCall = RetrofitConfig.httpApiInterface.getMealsWithType(type);
        responseCall.enqueue(new Callback<BaseResponse<List<Map<String, List<Food>>>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Map<String, List<Food>>>>> call, Response<BaseResponse<List<Map<String, List<Food>>>>> response) {
                onDataResponse(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Map<String, List<Food>>>>> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void addMeal(int mealId, final RequestCallback callback) {
        Call<BaseResponse> responseCall = RetrofitConfig.httpApiInterface.addMeal(mealId);
        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                onDataResponseWithoutType(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }

    @Override
    public void getAbout(final RequestCallback<String> resutl) {
        Call<BaseResponse<String>> responseCall = RetrofitConfig.httpApiInterface.getWebPage("about");
        responseCall.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                onDataResponse(response, resutl);
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                onDataFailure(t, resutl);
            }
        });
    }

    @Override
    public void contactUs(String question, final RequestCallback callback) {
        Call<BaseResponse> responseCall = RetrofitConfig.httpApiInterface.contactUs(question);
        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                onDataResponseWithoutType(response, callback);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onDataFailure(t, callback);
            }
        });
    }
}
