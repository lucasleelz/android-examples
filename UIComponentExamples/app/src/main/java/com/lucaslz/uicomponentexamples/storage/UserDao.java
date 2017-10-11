package com.lucaslz.uicomponentexamples.storage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/11.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM USERS_")
    List<User> findAll();

    @Query("SELECT * FROM USERS_ WHERE USERNAME_ LIKE :username")
    User findByUsername(String username);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
