package com.kalay.shift.shift;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.lang.reflect.Array;

public class prefrencesChangesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrences_changes);

        //read input array
        String names[] = {"גולף", "טניס", "שחייה"};
        System.out.println("ספורט:");
        for (int i=0; i< names.length ;i++)
        {
            //create the UI check box
            final LinearLayout ll = findViewById(R.id.linearLayoutId);
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(names[i]);
            ll.addView(cb);

        }
    }
}
