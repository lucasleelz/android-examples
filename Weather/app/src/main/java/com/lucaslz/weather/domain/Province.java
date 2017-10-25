package com.lucaslz.weather.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * 省。
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/23.
 */
@Entity(tableName = "PROVINCES_")
public class Province {

    @PrimaryKey
    @ColumnInfo(name = "ID_")
    private Integer id;

    @SerializedName("name")
    @ColumnInfo(name = "NAME_")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
