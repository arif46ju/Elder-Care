package com.example.eldercare;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

public class myAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final MediaPlayer mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();



        //notification
        Intent intent1 = new Intent(context, myAlarm.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(context);

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notofication_bell)
                .setTicker("ElderCare")
                .setContentTitle("it's medication time")
                .setContentText("you need to take your medicin now")
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());

//NotificationHelper notificationHelper=new NotificationHelper();
        CountDownTimer timer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // Nothing to do
            }

            @Override
            public void onFinish() {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                   mediaPlayer.release();
                }
            }
        };
        timer.start();

        // mediaPlayer.stop();

    }
}
