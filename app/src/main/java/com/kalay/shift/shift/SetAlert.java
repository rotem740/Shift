package com.kalay.shift.shift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
        EditText editText1 = (EditText) v.findViewById(R.id.editText);
        int key = 1000;
        String [] arr = {"DFdf", "fdfd"};
        AlertsSaver alert = new AlertsSaver(editText1.getText().toString(), arr, arr);
        }

    }



