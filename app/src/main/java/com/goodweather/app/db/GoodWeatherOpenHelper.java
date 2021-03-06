package com.goodweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建数据库和表
 * 创建三张表,存放省市县的各种信息
 *
 * Created by wangkaiyan on 16/5/17.
 */
public class GoodWeatherOpenHelper extends SQLiteOpenHelper {

    /**
     * Province表建表语句
     */
    public static final String CREATE_PROVINCE = "create table Province("
            + "id integer primary key autoincrement"
            + "province_name text"
            + "province_code text)";

    /**
     * City表建表语句
     */
    public static final String CREATE_CITY = "create table City("
            + "id integer primary key autoincrement"
            + "city_name text"
            + "city_code text"
            +"province_id integer)";

    /**
     * County表建表语句
     */
    public static final String CREATE_COUNTY = "create table County("
            + "id integer primary key autoincrement"
            + "county_name text"
            + "county_code text"
            + "city_id integer)";

    public GoodWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建Province,City,County三张表
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
