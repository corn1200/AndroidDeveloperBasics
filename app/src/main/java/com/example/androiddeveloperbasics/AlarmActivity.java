package com.example.androiddeveloperbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AlarmActivity extends AppCompatActivity {
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

//        액션바 세팅
        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton buttonView, boolean isChecked) {
                        String toastMessage;
                        if (isChecked) {
                            deliverNotification(AlarmActivity.this);
                            // Set the toast message for the "on" case.
                            toastMessage = "Stand Up Alarm On!";
                        } else {
                            // Cancel notification if the alarm is turned off
                            mNotificationManager.cancelAll();

                            // Set the toast message for the "off" case.
                            toastMessage = "Stand Up Alarm Off!";
                        }

                        // Show a toast to say the alarm is turned on or off.
                        Toast.makeText(AlarmActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        createNotificationChannel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return new MainToolbar().onSelectItems(item, this);
    }

    public void createNotificationChannel() {
        // Create a notification manager object.
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.O) {
            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID, "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every 15 minuets to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void deliverNotification(Context context) {
        Intent contentIntent = new Intent(context, AlarmActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_stand_up)
                        .setContentTitle("Stand Up Alert")
                        .setContentText("You should stand up and walk around now!")
                        .setContentIntent(contentPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}