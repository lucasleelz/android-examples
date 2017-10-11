package com.lucaslz.uicomponentexamples.storage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/11.
 */
@Entity(tableName = "USERS_")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_")
    private int id;

    @ColumnInfo(name = "USERNAME_")
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
