package com.kalay.shift.shift;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romdolinger on 5/6/18.
 */

public class FieldsOfInterest {
    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
    private List<String> info;
    private static String key = "4000";

    public FieldsOfInterest(Activity activity, List<String> fieldsOf) {
        this.info = new ArrayList<>();
        for (String obj : fieldsOf)
            this.info.add(obj);
        sharedPreferencesManager.storeData(activity, key, this.info);
    }

    public List<String> getFields() {
        return this.info;
    }

    public void setFields(Activity activity, List<String> fieldsOf) {
        this.info.clear();
        for (String obj : fieldsOf)
            this.info.add(obj);
        sharedPreferencesManager.storeData(activity, key, this.info);
    }

    public void addField(Activity activity, String fieldsOf) {
        this.info.add(fieldsOf);
        sharedPreferencesManager.storeData(activity, key, this.info);
    }
}