package com.kalay.shift.shift;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by romdolinger on 5/4/18.
 */

public class AlertsSaver {

    private List<Object> alert;
    private String key;
    private static int info=0;
    private static int hours=2;
    private static int days=1;
    private static Activity activity;
    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();

    public AlertsSaver(Activity activity,String alert, String[] hours_arr, String [] days_arr) {
        if (alert != null && hours_arr.length == 2 && days_arr != null) {
            this.alert.add(alert);
            this.alert.add(days_arr);
            this.alert.add(hours_arr);
            this.key = this.sharedPreferencesManager.nextEmpty(activity);
            this.sharedPreferencesManager.storeData(activity, this.key, this.alert);
        }

    }

    public AlertsSaver(Activity activity, String key) {
        List<Object> alertGet = (List) sharedPreferencesManager.getStoredData(activity, key);
        this.alert.add(alertGet.get(info));
        this.alert.add(alertGet.get(days));
        this.alert.add(alertGet.get(days));
        this.key = key;
    }

    public void setInfo(Activity activity, String userInfo) {
        this.alert.set(info, userInfo);
        sharedPreferencesManager.storeData(activity, this.key, this.alert);

    }

    public void setHours(Activity activity, String [] userInfo) {
        this.alert.set(hours, userInfo);
        sharedPreferencesManager.storeData(activity, this.key, this.alert);

    }

    public void setDays(Activity activity, String [] userInfo) {
        this.alert.set(days, userInfo);
        sharedPreferencesManager.storeData(activity, this.key, this.alert);

    }

    public List<Object> getAlert() {
        return this.alert;
    }

    public Object getAlertText() {
        return this.alert.get(info);
    }

    public Object getAlertDays() {
        return this.alert.get(days);
    }

    public Object getAlertHours() {
        return this.alert.get(hours);
    }

    public static int getInfo() {
        return info;
    }

    public static void setInfo(int infoUser) {
        info = infoUser;
    }

    public static int getHours() {
        return hours;
    }

    public static void setHours(int hoursUser) {
        hours = hoursUser;
    }

    public static int getDays() {
        return days;
    }

    public static void setDays(int daysUser) {
        days = daysUser;
    }

    public void deleteAlert(Activity activity) {
        sharedPreferencesManager.deleteAlert(activity, this.key);
    }

    public static String findByContent(Activity activity, String alertInfo) {
        return sharedPreferencesManager.findByInfo(activity, alertInfo);
    }

    public static String nextEmpty(Activity activity) {
        return sharedPreferencesManager.nextEmpty(activity);
    }

    @Override
    public String toString() {
        String s = "Alert: [key: " + this.key + " ";
        for (Object  part: this.alert) {
            s += String.valueOf(part);
        }
        s += "]";
        return s;
    }
}

