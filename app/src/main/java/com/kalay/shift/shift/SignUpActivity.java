package com.kalay.shift.shift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onNextSignUp(View v) {
        Log.d("onNext","hello from on next");
        Intent intent = new Intent(getApplicationContext(), InterestsActivity.class);
        startActivity(intent);
    }
}
