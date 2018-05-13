package com.kalay.shift.shift;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by romdolinger on 5/4/18.
 */

public class AlertsSaver {

    private static List<String> deleted;
    private Alert alert;
    private String key;
    public static final int startKey = 1000;

    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();

    public AlertsSaver(Activity activity,String alert, String[] hours_arr, boolean [] days_arr) {
        if (alert != null && hours_arr.length == 2 && days_arr != null) {
            this.alert = new Alert(alert, days_arr, hours_arr);
            this.key = this.sharedPreferencesManager.nextEmpty(activity);
            if (deleted.contains(this.key))
                this.updateDeleted();
            this.sharedPreferencesManager.storeData(activity, this.key, this.alert);
        }

    }

    private void updateDeleted() {
        boolean changed = false;
        int place = 0;
        for (String obj: deleted) {
            if (changed == false && obj.toString().equals(this.key)) {
                deleted.set(place, null);
                changed = true;
            }
            else if (changed == true)
                deleted.set(place - 1, obj);
            place++;
        }
    }

    public AlertsSaver(Activity activity, String key) {
        List<Object> alertGet = (List) sharedPreferencesManager.getStoredData(activity, key);
        this.alert = new Alert(alertGet.get(0).toString(), toBooleanArray(alertGet.get(1).toString()), alertGet.get(2).toString().split(","));
        this.key = key;
        if (deleted.contains(this.key))
            this.updateDeleted();
    }


    private static boolean[] toBooleanArray (String str) {
        String[] parts = str.split(",");
        boolean[] array = new boolean[parts.length];
        for (int i = 0; i < parts.length; i++)
            array[i] = Boolean.parseBoolean(parts[i]);
        return array;
    }
    public void setInfo(Activity activity, String userInfo) {
        this.alert.setText(userInfo);
        sharedPreferencesManager.storeData(activity, this.key, this.alert);

    }

    public void setHours(Activity activity, String [] userInfo) {
        this.alert.setHours(userInfo);
        sharedPreferencesManager.storeData(activity, this.key, this.alert);

    }

    public void setDays(Activity activity, boolean [] userInfo) {
        if (userInfo.length == 7) {
            this.alert.setDays(userInfo);
            sharedPreferencesManager.storeData(activity, this.key, this.alert);
        }
        else
            throw new RuntimeException("userInfo length must be 7. Length: " + userInfo.length);

    }

    public Alert getAlert() {
        return this.alert;
    }

    public Object getAlertText() {
        return this.alert.getText();
    }

    public boolean [] getAlertDays() {
        return this.alert.getDays();
    }


    public Object getAlertHours() {
        return this.alert.getHours();
    }

    public void deleteAlert(Activity activity) {
        deleted.add(this.key);
        sharedPreferencesManager.storeData(activity, "3000", deleted);
        sharedPreferencesManager.deleteAlert(activity, this.key);
    }

    public static List<String> returnDeltedPlaces(Activity activity) {
        List<String> toReturn = new ArrayList<>();
        for (String obj : deleted)
            toReturn.add(obj);
        return toReturn;
    }

    public static String findByContent(Activity activity, String alertInfo) {
        return sharedPreferencesManager.findByInfo(activity, alertInfo);
    }

    public static String nextEmpty(Activity activity) {
        return sharedPreferencesManager.nextEmpty(activity);
    }

    @Override
    public String toString() {
        return "AlertsSaver{" +
                "alert=" + alert +
                ", key='" + key + '\'' +
                '}';
    }
}

