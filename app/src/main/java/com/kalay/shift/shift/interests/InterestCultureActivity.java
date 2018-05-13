package com.kalay.shift.shift.interests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kalay.shift.shift.R;

/**
 * Created by User on 06/05/2018.
 */

public class InterestCultureActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_culture);
    }

    public void onClose(View v){
        finish();
    }
}
