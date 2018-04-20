package com.kalay.shift.shift;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (bindService(new Intent(this, LocalService.class),
                mConnection, Context.BIND_AUTO_CREATE)) {
        } else {
            Log.e("MY_APP_TAG", "Error: The requested service doesn't " +
                    "exist, or this client isn't allowed access to it.");
        }
        startService(new Intent(this, LocalService.class));

    }

    static LocalService mBoundService;


    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mBoundService = ((LocalService.LocalBinder)service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
        }
    };



    public void onStart(View v) {
        Log.d("onStart","Hello from on start");
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        sendBroadcast(intent);
        startActivity(intent);
    }


}
