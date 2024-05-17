package com.mao.myservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mao.myservice.service.MyMusicService;

public class MusicActivity extends AppCompatActivity {
    private MyMusicService musicService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            musicService = ((MyMusicService.LocalBinder) iBinder).getService();
            musicService.playAudio(R.raw.flower);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Button plyBtn = findViewById(R.id.play_music);
        plyBtn.setOnClickListener(v -> {
            Intent bindIntent = new Intent(this, MyMusicService.class);
            bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
//            bindIntent.putExtra("resource_id", R.raw.flower);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                musicService.playAudio(R.raw.flower);
                startForegroundService(bindIntent);
            }else {
                startService(bindIntent);
            }
        });
    }
}