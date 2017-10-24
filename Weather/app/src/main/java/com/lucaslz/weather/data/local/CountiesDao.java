package com.lucaslz.weather.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.lucaslz.weather.domain.County;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
@Dao
public interface CountiesDao {

    @Insert
    void save(County... counties);

    @Query("SELECT * FROM COUNTIES_")
    List<County> findTasks();
}
