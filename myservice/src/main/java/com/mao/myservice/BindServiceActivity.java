package com.mao.myservice;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.myservice.service.MyBindService;
import com.mao.myservice.service.MyStartService;

public class BindServiceActivity extends AppCompatActivity {
    public MyBindService myBindService;
    public boolean isBind = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        findViewById(R.id.btn_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBind) {
                    Intent intent = new Intent(BindServiceActivity.this, MyBindService.class);
                    bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                    isBind = true;
                }
            }
        });

        findViewById(R.id.btn_unBind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBind) {
                    unbindService(serviceConnection);
                    isBind = false;
                    myBindService = null;
                }
            }
        });
    }

    // 绑定服务时，需要创建服务连接
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            Log.e(getClass().getName(), "onServiceConnected");
            myBindService = ((MyBindService.LocalBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(getClass().getName(), "onServiceDisconnected");
            myBindService = null;
        }
    };
}
