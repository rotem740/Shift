package com.kalay.shift.shift;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;



public class LocalService extends Service {

    private NotificationManager mNM;
    private int NOTIFICATION = 0;


    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Calendar today = Calendar.getInstance();
        int hour = 19;
        int minute = 49;
        int second = 0;
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, minute);
        today.set(Calendar.SECOND, second);
        Timer timer = new Timer();
        timer.schedule(new MyNotificationManager(this), today.getTime());
    }

    @Override
    public void onDestroy(){
        Log.i("LocalService", "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        PendingIntent contentIntent = null;
        Notification notification = new Notification.Builder(this)
                .setTicker("Shift פועל")
                .setSmallIcon(R.drawable.notification_permanent_logo)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Shift פועל")
                .setContentText("ישומון ההתראות פעיל כעת")
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .build();
        startForeground(10, notification);

        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new LocalBinder();


public class MyNotificationManager extends TimerTask {

     Context ctx;

     MyNotificationManager(Context _ctx) {
         ctx = _ctx;
     }

     public void run() {
         CharSequence text = "נראה לנו שזה הזמן המתאים!";
         CharSequence title = "מתי בפעם האחרונה יצאת לטיול?";
         Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
         PendingIntent contentIntent = null;
         Notification notification = new Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.notification_logo)
                    .setTicker(text)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(title)
                    .setContentText(text)
                    .setContentIntent(contentIntent)
                    .setSound(soundUri)
                    .setPriority(Notification.PRIORITY_MAX)
                    .build();

         mNM.notify(NOTIFICATION, notification);
     }
    }
}