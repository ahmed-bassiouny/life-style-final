package lifestyle.com.lifestyle.base.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;

import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.helper.Utils;


public abstract class BaseController implements IBaseController {

    private IFragment iFragment;
    private IActivity iActivity;
    private Activity activity;


    public BaseController(Activity activity,Fragment fragment) {
        this.activity = activity;
        iFragment = (IFragment) fragment;
    }
    public BaseController(Activity activity) {
        this.activity = activity;
        this.iActivity = (IActivity) activity;
    }


    public boolean networkAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public interface IResult<T>{
        void result(T t);
    }
    public interface IResultWithTotalItem<T>{
        void result(T t);
        void totalItemCount(int total);
    }
    public IFragment getFragment(){
        return iFragment;
    }


    public void launchActivity(Class<?> myClass){
        activity.startActivity(new Intent(activity,myClass));
    }
    public void launchActivityWithFinish(Class<?> myClass){
        activity.startActivity(new Intent(activity,myClass));
        finishctivity();
    }
    public void launchActivityWithFinishAndClearStack(Class<?> myClass){
        Intent intent = new Intent(activity,myClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        finishctivity();
    }
    public void finishctivity(){
        activity.finish();
    }

    public void launchActivity(Class<?> myClass,Bundle bundle){
        Intent intent = new Intent(activity,myClass);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public Activity getActivity() {
        return  activity;
    }

    public IActivity getiActivity() {
        return iActivity;
    }

    @Override
    public void showMessage(String msg) {
        Alerter.create(activity)
                .setText(Utils.checkString(msg))
                .setBackgroundColorRes(R.color.colorPrimary)
                .show();
    }

    @Override
    public void showSuccessMessage(String msg) {
        Alerter.create(activity)
                .setText(Utils.checkString(msg))
                .setBackgroundColorRes(R.color.green)
                .show();
    }



    @Override
    public void showAlertConnection() {
        Alerter.create(activity)
                .setText("ﻻ يوجد اتصال بالانترنت")
                .setIcon(R.drawable.no_connection)
                .setBackgroundColorRes(R.color.red)
                .show();
    }
    @Override
    public void showErrorMessage(String msg) {
        Alerter.create(activity)
                .setText(Utils.checkString(msg))
                .setBackgroundColorRes(R.color.red)
                .show();
    }


    @Override
    public void showAlertConnectionWithAction(String msg,View.OnClickListener listener) {
        Alerter.create(activity)
                .setText(Utils.checkString(msg))
                .setBackgroundColorRes(R.color.colorAccent)
                .addButton("Try Again", R.style.AlertButton, listener)
                .show();
    }

}
