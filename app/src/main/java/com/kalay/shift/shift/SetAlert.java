package com.kalay.shift.shift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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
        //todo moves to AddPersonalTime class
        Intent intent = new Intent(getApplicationContext(), AddPersonalTime.class);
        startActivity(intent);
    }

    public void addD (View v) {
        //todo adds and saves the new alert
        EditText editText1 = (EditText) v.findViewById(R.id.editText);
        EditText editText2 = (EditText) v.findViewById(R.id.editText);
        String key = AlertsSaver.nextEmpty(this);
        String text1 = editText1.getText().toString();
        String text2 = editText2.getText().toString();
        if (text1 != null && text2 != null) {
            AlertsSaver alert = new AlertsSaver(this, text1, AlertsSaver.hours, AlertsSaver.days, text2);
            Toast.makeText(this, "ALERT " + text2 + " SAVED", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "ONE OF THE FIELDS IS EMPTY", Toast.LENGTH_SHORT).show();
        }

    }



