package com.kalay.shift.shift;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;



/**
 * Created by romdolinger on 4/14/18.
 */

public class AddPersonalTime extends AppComapatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_time);
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"first alert", "2", "3"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }


}
