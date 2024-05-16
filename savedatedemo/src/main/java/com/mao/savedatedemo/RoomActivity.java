package com.mao.savedatedemo;

import android.app.usage.NetworkStatsManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mao.savedatedemo.Dao.Impl.UserDaoImpl;
import com.mao.savedatedemo.Entity.User;

public class RoomActivity extends AppCompatActivity {
    private UserDaoImpl userDaoImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room);
    }

    public void insertData(View view){
        User user1 = new User("张三", "888888");
        User user2 = new User("张四", "888888");
        User user3 = new User("张五", "888888");
        userDaoImpl.insertUsers(user1, user2, user3);
    }
}