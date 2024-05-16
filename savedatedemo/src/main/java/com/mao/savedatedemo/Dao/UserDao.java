package com.mao.savedatedemo.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mao.savedatedemo.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert()
    void insertUser(User... users);

    @Query("delete from user where _id in (:ids)")
    void deleteSomeUser(int[] ids);

    @Delete
    void deleteUser(User... users);

    @Update
    void updateUser(User... users);

    @Query("delete from user")
    void deleteAllUser();

    @Query("select * from user where username=:username")
    List<User> selectUserByName(String username);

    @Query("select * from user")
    List<User> selectAllUsers();
}