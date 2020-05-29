package com.myapplicationdev.android.p05_ps;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class ScheduledNotificationReceiver extends BroadcastReceiver {
    int reqCode = 12345;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        Intent i = new Intent(context, Add.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, reqCode, i, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        String title = intent.getStringExtra("notification");
        builder.setContentTitle("Task Manager Reminder");
        builder.setContentText(title);
        builder.setLargeIcon(BitmapFactory. decodeResource (context.getResources() , android.R.drawable.btn_star_big_on ));
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);

        Notification n = builder.build();
        notificationManager.notify(123, n);
    }

}
