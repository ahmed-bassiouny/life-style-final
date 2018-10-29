package lifestyle.com.lifestyle.api;

import java.util.List;
import java.util.Map;

import lifestyle.com.lifestyle.base.api.BaseList;
import lifestyle.com.lifestyle.base.api.BaseResponse;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.FoodsId;
import lifestyle.com.lifestyle.model.Meal;
import lifestyle.com.lifestyle.model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ProjectApi {

    @POST("login")
    @FormUrlEncoded
    Call<BaseResponse<User>> login(@Field("email") String email, @Field("password") String password);

    @POST("forget_password")
    @FormUrlEncoded
    Call<BaseResponse> forgetPassword(@Field("email") String email);

    @POST("profile/edit")
    Call<BaseResponse<User>> editProfile(@Body User user);

    @POST("profile/edit")
    @Multipart
    Call<BaseResponse<User>> editProfile(@Part MultipartBody.Part file, @Part("name") RequestBody name
            , @Part("email") RequestBody email
            , @Part("phone") RequestBody phone, @Part("country") RequestBody country
            , @Part("city") RequestBody city, @Part("current_weight") RequestBody currentWeight
            , @Part("height") RequestBody height, @Part("gender") RequestBody gender
            , @Part("work_type") RequestBody workType, @Part("purpose") RequestBody purpose
            , @Part("sleeping_type") RequestBody sleepingType, @Part("sleeping_at") RequestBody sleepingAt
            , @Part("wakes_up_at") RequestBody wakesUpAt
            , @Part("is_approved") RequestBody isApproved, @Part("birthday") RequestBody birthday);

    @POST("registration")
    Call<BaseResponse<User>> register(@Body User user);


    @POST("password/edit")
    @FormUrlEncoded
    Call<BaseResponse> editPassword(@Field("password") String Password, @Field("old_password") String oldPassword);

    @POST("weight/edit")
    @FormUrlEncoded
    Call<BaseResponse> editWeight(@Field("current_weight") String currentWeight, @Field("goal_weight") String goalWeight);

    @POST("meals")
    @FormUrlEncoded
    Call<BaseResponse<BaseList<Meal>>> getMeals(@Field("meal") String meal, @Field("offset") int offset, @Field("limit") int limit);

    @POST("page")
    @FormUrlEncoded
    Call<BaseResponse<String>> getWebPage(@Field("name") String page);

    @POST("ask-question")
    @FormUrlEncoded
    Call<BaseResponse> contactUs(@Field("question") String question);

    @POST("meals/user/select")
    @FormUrlEncoded
    Call<BaseResponse> addMeal(@Field("meal_id") int mealId);


    @POST("meals/create")
    @FormUrlEncoded
    Call<BaseResponse<BaseList<Food>>> getFoods(@Field("type") String type);

    @POST("meals/create/by/user")
    Call<BaseResponse> createMeal(@Body FoodsId foodsId);

}
