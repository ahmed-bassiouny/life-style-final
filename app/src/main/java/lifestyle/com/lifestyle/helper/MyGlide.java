package lifestyle.com.lifestyle.helper;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MyGlide {
    public static void setImage(ImageView image, String url) {
        Glide.with(image.getContext()).load(url).into(image);
    }

    public static void setImageRound(ImageView image, Uri uri) {
        Glide.with(image.getContext()).load(uri).apply(RequestOptions.circleCropTransform()).into(image);
    }

    public static void setImageRound(ImageView image, String url) {
        Glide.with(image.getContext()).load(url).apply(RequestOptions.circleCropTransform()).into(image);
    }

    @SuppressLint("CheckResult")
    public static void setImageRoundWithPlaceholder(ImageView image, String url) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(image.getDrawable());
        requestOptions.circleCrop();
        Glide.with(image.getContext()).load(url).apply(requestOptions).into(image);
    }

    @SuppressLint("CheckResult")
    public static void setImagePlaceholder(ImageView image, String url) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(image.getDrawable());
        requestOptions.dontAnimate();
        Glide.with(image.getContext()).load(url).apply(requestOptions).into(image);
    }
}
