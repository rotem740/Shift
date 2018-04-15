package com.kalay.shift.shift;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Iterator;
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

}
