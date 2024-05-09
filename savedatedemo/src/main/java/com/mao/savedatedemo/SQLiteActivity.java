package com.mao.savedatedemo;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity {

    private SQLiteOpenHelper helper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);


    }

    // 创建数据库
    public void createDB(View view){
        SQLiteOpenHelper sqLiteOpenHelper1 = MySQLiteOpenHelper.getSqLiteOpenHelper(this);
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
//        SQLiteDatabase writableDatabase = helper.getWritableDatabase();

        System.out.println(helper.getDatabaseName());
    }

    public void insertDB(View view){
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

    public void selecetDB(View view){
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

        @SuppressLint("Range")
        public List selectDBToListView(View view){
            database = helper.getReadableDatabase();

            ArrayList users = new ArrayList();
            if (database.isOpen()) {
                Cursor cursor = database.rawQuery("select * from user limit ?, ?", new String[]{String.valueOf(0), String.valueOf(20)});
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String password = cursor.getString(cursor.getColumnIndex("password"));

                    // 创建user类

                }
                cursor.close();
                database.close();
            }
        }
    }
}
