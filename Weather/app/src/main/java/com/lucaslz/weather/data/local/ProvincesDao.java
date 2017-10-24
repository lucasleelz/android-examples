package com.lucaslz.weather.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.lucaslz.weather.domain.Province;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
@Dao
public interface ProvincesDao {

    @Query("SELECT * FROM PROVINCES_")
    List<Province> findProvinces();

    @Query("DELETE FROM PROVINCES_")
    void deleteProvinces();

    /**
     * Insert a province in the database. If the task already exists, replace it.
     *
     * @param province the province to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProvince(Province province);
}
