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
import java.util.concurrent.TimeUnit;


public class LocalService extends Service {

    private NotificationManager mNM;
    private int NOTIFICATION = 0;
    private boolean done = false;


    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Calendar today = Calendar.getInstance();
        int hour = 13;
        int minute = 19;
        int second = 0;
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, minute);
        today.set(Calendar.SECOND, second);
        Timer timer = new Timer();
        if (!done)
            timer.schedule(new MyNotificationManager(this), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        else
            done = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
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
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setTicker(text)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(title)
                    .setContentText(text)
                    .setContentIntent(contentIntent)
                    .setSound(soundUri)
                    .setPriority(Notification.PRIORITY_MAX)
                    .build();
            startForeground(NOTIFICATION, notification);
            mNM.notify(NOTIFICATION, notification);
     }
    }
}