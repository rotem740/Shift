package com.kalay.shift.shift;

import android.support.v7.app.AppCompatActivity;

import java.util.List;
/**
 * Created by romdolinger on 5/5/18.
 */

public class PersonalInfo extends AppCompatActivity {
    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
    private List<String> info;
    private static int namePlace = 0;
    private static int genderPlace = 1;
    private static String key = "2000";

    public PersonalInfo(String name, String gender) {
        this.info.add(name);
        this.info.add(gender);
        sharedPreferencesManager.storeData(this, key, this.info);
    }

    public String getName() {
        return this.info.get(namePlace);
    }

    public String getGender() {
        return this.info.get(genderPlace);
    }

    public void setName(String name) {
        this.info.set(namePlace, name);
        sharedPreferencesManager.storeData(this, key, this.info);
    }

    public void setGender(String gender) {
        this.info.set(genderPlace, gender);
        sharedPreferencesManager.storeData(this, key, this.info);
    }

}
