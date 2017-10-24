package com.lucaslz.weather.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 县。
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/23.
 */
@Entity(tableName = "COUNTIES_")
public class County {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_")
    private int id;

    @ColumnInfo(name = "NAME_")
    private String name;

    @ColumnInfo(name = "WEATHER_ID_")
    private String weatherId;

    @ColumnInfo(name = "CITY_ID_")
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
