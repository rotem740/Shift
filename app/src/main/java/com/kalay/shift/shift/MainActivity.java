package com.kalay.shift.shift;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
=======

>>>>>>> Gal-branch
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, LocalService.class));
    }

<<<<<<< HEAD
    public void onStart(View v) {
        Log.d("onStart","hello from on start");
=======

        public void onStart(View v) {
        Log.d("onStart","Hello from on start");
>>>>>>> Gal-branch
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);

        sendBroadcast(intent);
        startActivity(intent);
    }


}
