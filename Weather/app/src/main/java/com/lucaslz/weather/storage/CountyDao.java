package com.lucaslz.weather.storage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.lucaslz.weather.domain.County;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
@Dao
public interface CountyDao {

    @Insert
    void save(County... counties);
}
