
package com.adam.app.alarmdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    
    private AlarmManagerBroadcastReceiver alarm =  null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        alarm = new AlarmManagerBroadcastReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onStartRepeat(View v) {
        Context context = this.getApplicationContext();
        
        if(alarm != null) {
            alarm.startAlarm(context);
        }
        else {
            Toast.makeText(this, "alarm is null..", Toast.LENGTH_SHORT).show();
        }
        
    }
    
    public void onStartCancel(View v) {
        Context context = this.getApplicationContext();
        
        if(alarm != null) {
            alarm.cancelAlarm(context);
        }
        else {
            Toast.makeText(this, "alarm is null..", Toast.LENGTH_SHORT).show();
        }
        
    }

    public void onStartOnce(View v) {
        Context context = this.getApplicationContext();
        
        if(alarm != null) {
            alarm.startOneTimeAlarm(context);
        }
        else {
            Toast.makeText(this, "alarm is null..", Toast.LENGTH_SHORT).show();
        }
        
    }

}
