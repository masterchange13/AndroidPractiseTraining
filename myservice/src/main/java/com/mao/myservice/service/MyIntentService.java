package com.mao.myservice.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(getClass().getName(), "onHandleIntent: ");
        for (int i = 0; i < 3; i++){
            try {
                Log.e(getClass().getName(), "onHandleIntent: " + i + " sleep");
                Thread.sleep(10000);
                Log.e(getClass().getName(), "onHandleIntent: " + i + " wake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(getClass().getName(), "onDestroy: ");
    }
}
