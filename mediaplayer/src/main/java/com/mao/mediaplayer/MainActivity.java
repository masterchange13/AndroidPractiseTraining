//package com.mao.mediaplayer;
//
//import android.content.pm.PackageManager;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.google.android.material.badge.BadgeUtils;
//
//import java.io.File;
//
//public class MainActivity extends AppCompatActivity {
//
//    private VideoView videoView;
//    private Button btnPlay, btnPause, btnReplay;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
//        btnPlay = findViewById(R.id.btnPlay);
//        btnPause = findViewById(R.id.btnPause);
//        btnReplay = findViewById(R.id.btnRePlay);
//        videoView = findViewById(R.id.vdwFilm);
//
//        // read file and get authority
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION)
//            !=PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(
//                        MainActivity.this,
//                        new String[]{Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION},
//                1);
//        } else{
//            initVidePath();
//        }
//    }
//
//    // if authority is okay, init video path, else remain user
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initVidePath();
//                }else{
//                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void initVidePath(){
////        File file = new File(Environment.getExternalStorageDirectory(),"/DCIM/Camera/").mkdirs();
//        videoView.setVideoURI(Uri.parse("android.resource://com.mao.mediaplayer/" + R.raw.pianzhang));
////        videoView.setVideoPath(Uri.parse("【瓜车佬差点交代在回家的路上，美好的假期必须有大海，这次带上橙子回家看海！骑行vlog】 https://www.bilibili.com/video/BV12x4y1B7Cg/?share_source=copy_web&vd_source=afdfbfb13725b10b02a83a18af2eb176"));
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mediaPlayer.setLooping(true);
//                videoView.start();
//            }
//        });
//
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                mediaPlayer.setLooping(true);
//                videoView.start();
//            }
//        });
//    }
//
//    public void onClick(View view) {
//        int id = view.getId();
//        if (id == R.id.btnPlay) {
//
//            if (!videoView.isPlaying()) {
//                videoView.start();
//            }
//        }
//        if (id == R.id.btnPause) {
//            if (videoView.isPlaying()) {
//                videoView.pause();
//            }
//        }
//        if (id == R.id.btnReplay) {
//            videoView.resume();
//        }
//    }
//}



















package com.mao.mediaplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView videoView;
    private Button btnPlay,btnPause,btnReplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.vdwFilm);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnReplay = findViewById(R.id.btnRePlay);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnReplay.setOnClickListener(this);

//       读取视频文件，赋予数据访问权限
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
//            赋予权限
//            也可以在AndroidManifest.xml 里面配置用户权限
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }else {
//            初始化 VideoPlayer
            initVidePath();
        }
    }

    //    对权限的获取结果进行判断，如果获取成功了，进行初始化，如果没有获取到权限，提示用户进行授权
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVidePath();
                }else {
                    Toast.makeText(this,"权限不足，拒绝读取内容",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    private void initVidePath(){
//        三种形式：本地根目录的资源  /   指定项目工程路径下资源  /  访问网络视频
//        1、根目录资源
/*        File file = new File(Environment.getExternalStorageDirectory(), "");
          videoView.setVideoPath(file.getPath());
        */
//        2、获取指定工程路径下的
        videoView.setVideoURI(Uri.parse("android.resource://com.mao.mediaplayer/"+ R.raw.pianzhang));
//        3、访问网络视频
//        videoView.setVideoURI(Uri.parse("https://www.bilibili.com/video/BV11F4m1P7hS/?share_source=copy_web&vd_source=8499517f2a6166c4cacd25b23af803f6"));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
//                播放视频
                videoView.start();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnPlay) {

            if (!videoView.isPlaying()) {
                videoView.start();
            }
        }
        if (id == R.id.btnPause) {
            if (videoView.isPlaying()) {
                videoView.pause();
            }
        }
        if (id == R.id.btnRePlay) {
            videoView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        释放资源
        if (videoView != null){
            videoView.suspend();
        }
    }
}
