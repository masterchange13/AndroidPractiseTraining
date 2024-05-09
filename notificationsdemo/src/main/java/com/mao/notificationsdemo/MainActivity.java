package com.mao.notificationsdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mao.notificationsdemo.notifications.NotificationActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // 通知和管理通知
    private NotificationManager notManager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelId = String.valueOf(new Random().nextInt());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) {
            NotificationChannel channel = new NotificationChannel(channelId, "通知", NotificationManager.IMPORTANCE_DEFAULT);
            notManager.createNotificationChannel(channel);
        }

        String text = "今日热搜";
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("今日热搜")
                .setContentText("xxxxxxx")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.alert_dark_frame))
                .setColor(Color.argb(255, 255, 0, 0))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setTicker(text)
                .setVibrate(new long[]{100, 200, 300, 400, 500})
                .build();
    }

    public void sendNotification(View view){
        notManager.notify(1, notification);
    }

    public void cellNotification(View view){
        notManager.cancel(1);
    }
}