package com.lucaslz.uicomponentexamples.storage;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by lucas on 2017/10/12.
 */

public class Address {

    @ColumnInfo(name = "STREET_")
    public String street;

    @ColumnInfo(name = "STATE_")
    public String state;

    @ColumnInfo(name = "CITY_")
    public String city;

    @ColumnInfo(name = "POST_CODE_")
    public int postCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }
}