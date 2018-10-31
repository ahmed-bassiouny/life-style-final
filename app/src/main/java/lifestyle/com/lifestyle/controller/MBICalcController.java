package lifestyle.com.lifestyle.controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.base.ui.BaseController;

public class MBICalcController extends BaseController {

    private Activity activity;

    public MBICalcController(Activity activity) {
        super(activity);
    }


    public List<Integer> getAges() {
        List<Integer> results = new ArrayList<>();
        for (int i = 10; i < 60; i++)
            results.add(i);
        return results;
    }
    public List<Integer> getHeight() {
        List<Integer> results = new ArrayList<>();
        for (int i = 140; i < 250; i++)
            results.add(i);
        return results;
    }

    public List<Integer> getWeight() {
        List<Integer> results = new ArrayList<>();
        for (int i = 40; i < 200; i++)
            results.add(i);
        return results;
    }

    public String getBMIStatus(float bmi){
        if (bmi < 18.5) {
            return "وزن خفيف";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "طبيعى";
        } else if (bmi >= 25 && bmi <= 29.9) {
            return "وزن زائد";
        } else if (bmi >= 30 && bmi <= 34.9) {
            return "بدين";
        } else if (bmi >= 35) {
            return "بدين للغاية";
        }
        return "";
    }
}
