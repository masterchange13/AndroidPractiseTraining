package com.mao.savedatedemo;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.savedatedemo.Entity.User;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity implements Runnable{

    private SQLiteOpenHelper helper;
    private SQLiteDatabase database;

    private ListView listView;

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        listView = findViewById(R.id.user_list);

        helper = MySQLiteOpenHelper.getSqLiteOpenHelper(this);

        selectDBToListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                selectDBToListView();
                adapter.notifyDataSetChanged();
            }
        });
    }

    // 创建数据库
    public void createDB(View view) {
        SQLiteOpenHelper sqLiteOpenHelper1 = MySQLiteOpenHelper.getSqLiteOpenHelper(this);
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
//        SQLiteDatabase writableDatabase = helper.getWritableDatabase();

        System.out.println(helper.getDatabaseName());
    }

    public void insertDB(View view) {
        database = helper.getWritableDatabase();

        if (database.isOpen()) {
            String sql1 = "insert into user(username, password) values ('joker', '66666')";
            String sql2 = "insert into user(username, password) values ('kitty', '66666')";
            String sql3 = "insert into user(username, password) values ('oktavia', '66666')";
            database.execSQL(sql1);
            database.execSQL(sql2);
            database.execSQL(sql3);
        }
        // close database
        database.close();
    }

    @SuppressLint("Range")
    public void selectDB(View view) {
        database = helper.getReadableDatabase();

        if (database.isOpen()) {
            Cursor cursor = database.rawQuery("select * from user", null);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                Log.i("database", "selectDB:_id:" + id + "username" + username + "password" + password);
            }
            cursor.close();
            database.close();
        }
    }

    @SuppressLint("Range")
    public void selectDBToListView() {
        database = helper.getReadableDatabase();

        ArrayList users = new ArrayList();
        if (database.isOpen()) {
            Cursor cursor = database.rawQuery("select * from user limit ?, ?", new String[]{String.valueOf(0), String.valueOf(20)});
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String password = cursor.getString(cursor.getColumnIndex("password"));

                // 创建user类
                User user = new User(id, username, password);
                users.add(user);
            }
            ArrayList<HashMap<String, Object>> data = new ArrayList<>();
            for (Object use: users) {
                User user = (User) use;
                HashMap<String, Object> item = new HashMap<>();
                item.put("userId", user.getId());
                item.put("name", user.getUsername());
                item.put("pwd", user.getPassword());
                data.add(item);
            }

            // 创建适配器
            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.item,
                    new String[]{"userId", "name", "pwd"}, new int[]{R.id.userId, R.id.name, R.id.pwd});

            // 将适配器交给listvew
            listView.setAdapter(adapter);


            cursor.close();
            database.close();
        }
    }

    public void deleteDate(View view){
        database = helper.getWritableDatabase();
        if (database.isOpen()) {
            String sql = "delete from user where _id = ?";
            database.execSQL(sql, new Object[]{1});
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }

        listView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return false;
            }

            @Override
            public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
                return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
            }

            public boolean onLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        database.close();
    }

    public void updateView(View view){
        database = helper.getWritableDatabase();
        if (database.isOpen()) {
            String sql = "update user set username = ?, password = ? where _id = ?";
            database.execSQL(sql, new Object[]{"123", "123", 1});
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // 刷新界面
            listView.postInvalidate();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
