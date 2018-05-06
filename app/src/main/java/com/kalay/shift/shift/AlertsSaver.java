package com.kalay.shift.shift;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romdolinger on 5/4/18.
 */

public class AlertsSaver {

    private static List<Object> deleted;
    private List<Object> alert;
    private String key;
    private static int info=0;
    private static int hours=2;
    private static int days=1;

    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();

    public AlertsSaver(Activity activity,String alert, String[] hours_arr, String [] days_arr) {
        if (alert != null && hours_arr.length == 2 && days_arr != null) {
            this.alert = new ArrayList<>();
            this.alert.add(alert);
            this.alert.add(days_arr);
            this.alert.add(hours_arr);
            this.key = this.sharedPreferencesManager.nextEmpty(activity);
            if (deleted.contains(this.key))
                this.updateDeleted();
            this.sharedPreferencesManager.storeData(activity, this.key, this.alert);
        }

    }

    private void updateDeleted() {
        boolean changed = false;
        int place = 0;
        for (Object obj: deleted) {
            if (changed == false && obj.toString().equals(this.key)) {
                deleted.set(place, null);
                changed = true;
            }
            else if (changed == true)
                this.alert.set(place - 1, obj);
            place++;
        }
    }

    public AlertsSaver(Activity activity, String key) {
        List<Object> alertGet = (List) sharedPreferencesManager.getStoredData(activity, key);
        this.alert.add(alertGet.get(info));
        this.alert.add(alertGet.get(days));
        this.alert.add(alertGet.get(days));
        this.key = key;
        if (deleted.contains(this.key))
            this.updateDeleted();
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
        deleted.add(this.key);
        sharedPreferencesManager.storeData(activity, "3000", deleted);
        sharedPreferencesManager.deleteAlert(activity, this.key);
    }

    public static List<Object> returnDeltedPlaces(Activity activity) {
        List<Object> toReturn = new ArrayList<>();
        for (Object obj : deleted)
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
        String s = "Alert: [key: " + this.key + " ";
        for (Object  part: this.alert) {
            s += String.valueOf(part);
        }
        s += "]";
        return s;
    }
}

