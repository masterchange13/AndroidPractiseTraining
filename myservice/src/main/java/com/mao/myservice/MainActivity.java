package com.mao.myservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mao.myservice.service.MyStartService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toStart(){
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }

    public void toBind(View view) {
        Intent intent = new Intent(this, BindServiceActivity.class);
        startActivity(intent);
    }

    public void toMusic(View view){
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }
}