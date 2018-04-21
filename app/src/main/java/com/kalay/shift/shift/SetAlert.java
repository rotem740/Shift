package com.kalay.shift.shift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import org.w3c.dom.Element;

/**
 * Created by romdolinger on 4/14/18.
 */

public class SetAlert extends AppCompatActivity {

    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alert);

    }

    public void moveToTime(View v) {
        Intent intent = new Intent(getApplicationContext(), AddPersonalTime.class);
        startActivity(intent);
    }

    public void addD (View v) {
        TextView textView1 = (TextView) v.findViewById(R.id.editText);
        sharedPreferencesManager = SharedPreferencesManager.getInstance();
        int key = 200;
        while (true) {
            if (sharedPreferencesManager.getStoredData(this, Integer.toString(key)) == null) {
                sharedPreferencesManager.storeData(this, Integer.toString(key), textView1);
                break;
            }
            key++;
        }

    }


}
