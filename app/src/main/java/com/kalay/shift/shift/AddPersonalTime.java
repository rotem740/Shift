package com.kalay.shift.shift;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.kalay.shift.shift.MainActivity;
import com.mcsoft.timerangepickerdialog.RangeTimePickerDialog;
import static com.kalay.shift.shift.SharePrefrencesTest.MyPREFERENCES;


/**
 * Created by romdolinger on 4/14/18.
 */

public class AddPersonalTime extends AppCompatActivity implements RangeTimePickerDialog.ISelectedTime {
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_time);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Toast.makeText(AddPersonalTime.this,"ed1: " + editor.toString() ,Toast.LENGTH_LONG).show();
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner);
        //create a list of items for the spinner.
        Integer[] items = new Integer[]{1,2,3};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        // Create an instance of the dialog fragment and show it
        RangeTimePickerDialog dialog = new RangeTimePickerDialog();
        dialog.newInstance();
        dialog.setRadiusDialog(20); // Set radius of dialog (default is 50)
        dialog.setIs24HourView(true); // Indicates if the format should be 24 hours
        dialog.setColorBackgroundHeader(R.color.colorPrimary); // Set Color of Background header dialog
        dialog.setColorTextButton(R.color.colorPrimaryDark); // Set Text color of button
        FragmentManager fragmentManager = getFragmentManager();
        dialog.show(fragmentManager, "");

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



                }
        }
    }


    @Override
    public void onSelectedTime(int hourStart, int minuteStart, int hourEnd, int minuteEnd)
    {
        Toast.makeText(this, "Start: "+hourStart+":"+minuteStart+"\nEnd: "+hourEnd+":"+minuteEnd, Toast.LENGTH_SHORT).show();
    }


}
