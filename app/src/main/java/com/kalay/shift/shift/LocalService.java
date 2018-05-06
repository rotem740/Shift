package com.kalay.shift.shift;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.util.Calendar;
import java.util.Random;
import java.util.TimerTask;


public class LocalService extends Service {

    private NotificationManager mNM;
    private int NOTIFICATION = 0;

    private int MAXHOUR = 19;
    private int MINHOUR = 7;
    Intent myIntent = new Intent(this , MyNotificationManager.class);
    AlarmManager alarmManager;
    PendingIntent pintent;
    //PendingIntent pintent = PendingIntent.getBroadcast( this, 0, new Intent("com.blah.blah.somemessage"), 0 );
    //PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);


    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        pintent = PendingIntent.getBroadcast( this, 0, new Intent("com.blah.blah.somemessage"), 0 );


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override public void onReceive( Context context, Intent _ )
            {
                //context.unregisterReceiver( this ); // this == BroadcastReceiver, not Activity
                Log.v("testAlarm","got here!");

                CharSequence text = "נראה לנו שזה הזמן המתאים!";
                CharSequence title = "מתי בפעם האחרונה יצאת לטיול?";
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                PendingIntent contentIntent = null;
                Notification notification = new Notification.Builder(context)
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
                TriggerNextAlarm();

            }
        };
        this.registerReceiver(receiver, new IntentFilter("com.blah.blah.somemessage") );
        TriggerNextAlarm();

    }

    private void TriggerNextAlarm() {
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar today = GetNotificationTime();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), AlarmManager.INTERVAL_DAY , pintent);

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


    public Calendar GetNotificationTime() {
        Calendar today = Calendar.getInstance();

        Random rnd = new Random();
        int hour;

        hour = (rnd.nextInt(MAXHOUR - MINHOUR) + 1 + MINHOUR);
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        // add one day to the date/calendar
        today.add(Calendar.DAY_OF_YEAR, 1);
        Log.i("LocalService", today.toString());

        return today;

    }


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