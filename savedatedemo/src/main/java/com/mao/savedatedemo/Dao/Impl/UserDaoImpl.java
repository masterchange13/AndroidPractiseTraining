package com.mao.savedatedemo.Dao.Impl;

import android.content.Context;
import android.os.AsyncTask;

import com.mao.savedatedemo.Dao.UserDao;
import com.mao.savedatedemo.DataBase.UserDataBase;
import com.mao.savedatedemo.Entity.User;

import java.util.List;

public class UserDaoImpl {
    private UserDao userDao;

    public UserDaoImpl(Context context) {
        UserDataBase userDataBase = UserDataBase.getUserDataBase(context);
        userDao = userDataBase.getUserDao();
    }

    public void insertUsers(User... users) {
        new InsertAsyncTask(userDao).execute(users);
    }

    public void selectAllUser(){
        new SelectAllUserAsyncTask(userDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users);
            return null;
        }
    }

    static class SelectAllUserAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDao userDao;

        @Override
        protected Void doInBackground(Void... voids) {
            List<User> users = userDao.selectAllUsers();
            return null;
        }
    }
}