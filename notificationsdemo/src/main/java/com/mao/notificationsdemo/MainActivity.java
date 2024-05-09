package com.mao.notificationsdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        notification = new NotificationCompat.Builder(this, channelId)
    }
}