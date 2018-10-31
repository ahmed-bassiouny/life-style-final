package lifestyle.com.lifestyle.controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

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

}
