package lifestyle.com.lifestyle.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import lifestyle.com.lifestyle.helper.Constants;
import lifestyle.com.lifestyle.model.User;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private static Retrofit retrofit;
    public static final String baseDomain = "http://esameisa.com/demo/healthystyle/";
    private static final String baseUrl = baseDomain+"api/";
    public static ProjectApi httpApiInterface;


    private RetrofitConfig() {
    }

    public static void initRetrofitConfig() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    User user = SharedPrefManager.getObject(Constants.USER, User.class);
                    HttpUrl url;
                    if (user == null) {
                        url = originalHttpUrl.newBuilder()
                                .build();
                    } else {
                        url = originalHttpUrl.newBuilder()
                                .addEncodedQueryParameter("user_id", String.valueOf(user.getId()))
                                .build();
                    }

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }).addInterceptor(interceptor)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            httpApiInterface = retrofit.create(ProjectApi.class);
        }
    }
}
