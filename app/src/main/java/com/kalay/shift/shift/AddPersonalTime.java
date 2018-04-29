package com.kalay.shift.shift;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.mcsoft.timerangepickerdialog.RangeTimePickerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romdolinger on 4/14/18.
 */

public class AddPersonalTime extends AppCompatActivity implements RangeTimePickerDialog.ISelectedTime {

    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_time);
        //create a list of items for the spinner.
        List<Object> myList = new ArrayList<>();
        sharedPreferencesManager = SharedPreferencesManager.getInstance();
        // Arbitrary value, this variable represents the first key of the alerts data series.
        //ERROR: When one data is deleted, the catch breaks the loop.
        int i = 100;
        while (true) {
            try {
                myList.add(sharedPreferencesManager.getStoredData(this, getString(i)));
            }
            catch (Exception e){
                break;
            }
            i++;
        }
        //read input array
        String names[] = {"שבת", "שישי", "חמישי", "רביעי" ,"שלישי", "שני",  "ראשון"};
        for (int j = 0; j < names.length ; j++) {
            //create the UI check box
            final LinearLayout ll = findViewById(R.id.linearLayoutId);
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(names[j]);
            ll.addView(cb);
        }
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        Spinner dropdown  = findViewById(R.id.spinner);
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, myList);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        FloatingActionButton b1=(FloatingActionButton) findViewById(R.id.fab);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the dialog fragment and show it
                RangeTimePickerDialog dialog = new RangeTimePickerDialog();
                dialog.newInstance();
                dialog.setRadiusDialog(20); // Set radius of dialog (default is 50)
                dialog.setIs24HourView(true); // Indicates if the format should be 24 hours
                dialog.setColorBackgroundHeader(R.color.colorPrimary); // Set Color of Background header dialog
                dialog.setColorTextButton(R.color.colorPrimaryDark); // Set Text color of button
                FragmentManager fragmentManager = getFragmentManager();
                dialog.show(fragmentManager, "");     }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK)
            {
                if (data.getExtras().containsKey(RangeTimePickerDialog.HOUR_START))
                {
                    int [] arr = new int [4];
                    arr[0] = data.getExtras().getInt(RangeTimePickerDialog.HOUR_START);
                    arr[1] = data.getExtras().getInt(RangeTimePickerDialog.HOUR_END);
                    arr[2] = data.getExtras().getInt(RangeTimePickerDialog.MINUTE_START);
                    arr[3] = data.getExtras().getInt(RangeTimePickerDialog.MINUTE_END);
                    int key = 300, count = 0;
                    while (true) {
                        if (sharedPreferencesManager.getStoredData(this, Integer.toString(key)) == null) {
                            sharedPreferencesManager.storeData(this, Integer.toString(key), arr[count]);
                            break;
                        }
                        key++;
                        count++;
                    }
                }
        }
    }


    @Override
    public void onSelectedTime(int hourStart, int minuteStart, int hourEnd, int minuteEnd) {
        Toast.makeText(this, "Start: "+hourStart+":"+minuteStart+"\nEnd: "+hourEnd+":"+minuteEnd, Toast.LENGTH_SHORT).show();
    }


}
