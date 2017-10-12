package com.lucaslz.uicomponentexamples.storage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lucas on 2017/10/12.
 */
@Entity(foreignKeys = @ForeignKey(
        entity = User.class, parentColumns = "ID_", childColumns = "USER_ID_"))
public class Book {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_")
    public int Id;

    @ColumnInfo(name = "USER_ID_")
    public int userId;

    @ColumnInfo(name = "TITLE_")
    public String title;
}
