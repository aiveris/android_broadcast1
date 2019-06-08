package com.example.broadcast1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void startAlarm (View v){
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 234, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (10000), pendingIntent);

            Toast.makeText(this, "Alarm Started", Toast.LENGTH_LONG).show();
        }

        public void sendBroadCast (View v){
            Log.e("BroadCasted", "self broadcast started...");
            Intent intent = new Intent();
            intent.setAction("com.apps.yo.broadcastalarmmanager.Self");
            intent.putExtra("info", "Yeah!");
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(intent);

            Toast.makeText(this, "...wait for Self Broadcast", Toast.LENGTH_LONG).show();
        }

        public void sendBroadCastAnotherApp (View v){
            Log.e("BroadCasted", "Send Broadcast to Another App...");
            Intent intent = new Intent();
            intent.setAction("com.apps.yo.broadcastalarmmanager");
            intent.putExtra("info", "Yeah!");
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(intent);

        }
}
