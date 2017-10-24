package com.lucaslz.weather.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * 省。
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/23.
 */
@Entity(tableName = "PROVINCES_", indices = {
        @Index(value = "CODE_", unique = true)
})
public class Province {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_")
    private int id;

    @ColumnInfo(name = "CODE_")
    private String code;

    @ColumnInfo(name = "NAME_")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
