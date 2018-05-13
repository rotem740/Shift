package com.kalay.shift.shift;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rotembr on 15/04/2018.
 */

public class SharedPreferencesManager {

    private static SharedPreferencesManager instance = null;

    private SharedPreferencesManager() {
        // Exists only to defeat instantiation.
    }
    public static SharedPreferencesManager getInstance() {
        if(instance == null) {
            instance = new SharedPreferencesManager();
        }
        return instance;
    }

    public void storeData(Activity activity, String key, Object data) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, data.toString());
        editor.commit();
    }

    public Object getStoredData(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public String nextEmpty (Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        int key = AlertsSaver.startKey;
        String data = sharedPref.getString(Integer.toString(key), null);
        while (data != null) {
                key++;
                data =  sharedPref.getString(Integer.toString(key), null);
        }
        return Integer.toString(key);
    }

    public void deleteAlert(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, null);
        editor.commit();
    }

    public String findByInfo(Activity activity, String info) {
        //Build only!
        return "1000";
    }

    public void storeData(Activity activity, Map<String, Object> keyDataMap) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Iterator it = keyDataMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            editor.putString((String) pair.getKey(), pair.getValue().toString());
            it.remove(); // avoids a ConcurrentModificationException
        }
        editor.commit();

    }

    public String returnKey(Activity activity, String obj){
        int key = AlertsSaver.startKey;
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        List<String> s = Arrays.asList(sharedPref.getString(Integer.toString(key), null).split(","));
        while (s.get(0) != obj)  {
            if (s == null)
                return "Not Exist!";
            key++;
            s = Arrays.asList(sharedPref.getString(Integer.toString(key), null).split(","));
        }
        return Integer.toString(key);
    }
}
