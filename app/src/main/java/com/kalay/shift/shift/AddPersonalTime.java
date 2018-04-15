package com.kalay.shift.shift;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.mcsoft.timerangepickerdialog.RangeTimePickerDialog;


/**
 * Created by romdolinger on 4/14/18.
 */

public class AddPersonalTime extends AppCompatActivity implements RangeTimePickerDialog.ISelectedTime {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_time);
        //create a list of items for the spinner.
        Integer[] items = new Integer[]{1,2,3};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        Spinner dropdown  = findViewById(R.id.spinner);
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK)
            {
                if (data.getExtras().containsKey(RangeTimePickerDialog.HOUR_START))
                {
                    int hourStart = data.getExtras().getInt(RangeTimePickerDialog.HOUR_START);
                    int hourEnd = data.getExtras().getInt(RangeTimePickerDialog.HOUR_END);
                    int minuteStart = data.getExtras().getInt(RangeTimePickerDialog.MINUTE_START);
                    int minuteEnd = data.getExtras().getInt(RangeTimePickerDialog.MINUTE_END);
                    Toast.makeText(AddPersonalTime.this,"W",Toast.LENGTH_LONG).show();
                }
        }
    }


    @Override
    public void onSelectedTime(int hourStart, int minuteStart, int hourEnd, int minuteEnd)
    {
        Toast.makeText(this, "Start: "+hourStart+":"+minuteStart+"\nEnd: "+hourEnd+":"+minuteEnd, Toast.LENGTH_SHORT).show();
    }


}
