package com.mao.savedatedemo.DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.mao.savedatedemo.Dao.UserDao;
import com.mao.savedatedemo.Entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao getUserDao() ;

    public UserDao userDao;

    private static UserDataBase userDataBase;

    public static UserDataBase getUserDataBase(Context context) {
        if (userDataBase == null) {
            userDataBase = Room.databaseBuilder(context.getApplicationContext(), UserDataBase.class, "userInfo")
                    .allowMainThreadQueries()
                    .build();
        }

        return userDataBase;
    }

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}