package com.adam.app.alarmdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    public static final String ONE_TIME = "onetime";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Alarm");
        
        //Aquire wake lock
        wl.acquire();
        
        Bundle extras = intent.getExtras();
        StringBuilder msgStr = new StringBuilder();
        
        if(extras != null && extras.getBoolean(ONE_TIME)) {
            msgStr.append("One time Timer: ");
        }
        
        Format formatter = new SimpleDateFormat("hh::mm::ss a");
        msgStr.append(formatter.format(new Date()));
        
        Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show();
        
        
        
        //Release wake lock
        wl.release();
    }
    
    public void startAlarm(Context context) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent =  new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pi);
    }
    
    public void cancelAlarm(Context context) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent =  new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.cancel(pi);
    }
    
    public void startOneTimeAlarm(Context context) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent =  new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.TRUE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
        
    }

}
