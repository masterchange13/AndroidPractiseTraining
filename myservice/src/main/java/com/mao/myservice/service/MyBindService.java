package com.mao.myservice.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.mao.myservice.BindServiceActivity;

import java.util.Random;

public class MyBindService extends Service {
    private NotificationManager manager;
    private Notification notification;

    private int NOTIFICATION = R.string.local_service_started;

    public final IBinder mBinder = new LocalBinder();

    // 自定义一个类实现localBinder
    public class LocalBinder extends Binder{
        public MyBindService getService(){
            return MyBindService.this;
        }
    }

    @Override
    public void onCreate() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Log.e(getClass().getName(), "onCreate");
        showNotification();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // 创建通知
    private void showNotification(){
        CharSequence text = getText(NOTIFICATION);
        String channelId = String.valueOf(new Random().nextInt());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) {
            NotificationChannel channel = new NotificationChannel(channelId, "My Service", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, BindServiceActivity.class), PendingIntent.FLAG_IMMUTABLE);
        notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mimap.ic_launcher)
                .setTicker(text)
                .setWhen(System.currentTimeMillis())
                .setContentText(text)
                .setContentTitle(getText(R.string.local_service_started))
                .build();
        manager.notify(NOTIFICATION, notification);
        Log.e(getClass().getName(), "通知已发出");
    }
}