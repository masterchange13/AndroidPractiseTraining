package com.mao.myservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyStartService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /*
     * 服务被启动时调用
     * intent
     * flags 指定绑定时是否自动创建Service, 0表示不自动创建, 1表示自动创建
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(getClass().getName(), "onStartCommand......");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(getClass().getName(), "onDestroy......");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
