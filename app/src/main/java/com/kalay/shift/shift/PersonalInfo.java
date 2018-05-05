package com.kalay.shift.shift;


import android.app.Activity;

import java.util.List;
/**
 * Created by romdolinger on 5/5/18.
 */

public class PersonalInfo  {
    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
    private List<String> info;
    private static int namePlace = 0;
    private static int genderPlace = 1;
    private static String key = "2000";

    public PersonalInfo(Activity activity, String name, String gender) {
        this.info.add(name);
        this.info.add(gender);
        sharedPreferencesManager.storeData(activity, key, this.info);
    }

    public String getName() {
        return this.info.get(namePlace);
    }

    public String getGender() {
        return this.info.get(genderPlace);
    }

    public void setName(Activity activity,String name) {
        this.info.set(namePlace, name);
        sharedPreferencesManager.storeData(activity, key, this.info);
    }

    public void setGender(Activity activity, String gender) {
        this.info.set(genderPlace, gender);
        sharedPreferencesManager.storeData(activity, key, this.info);
    }


}
