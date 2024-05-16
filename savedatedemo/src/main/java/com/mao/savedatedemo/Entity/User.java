package com.mao.savedatedemo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import javax.xml.transform.sax.SAXResult;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    public int _id;
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String password;

    public User(int id, String username, String password) {
        this._id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
