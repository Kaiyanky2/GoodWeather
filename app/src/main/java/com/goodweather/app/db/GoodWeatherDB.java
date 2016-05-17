package com.goodweather.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.goodweather.app.model.City;
import com.goodweather.app.model.County;
import com.goodweather.app.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装数据库操作
 *
 * Created by wangkaiyan on 16/5/17.
 */
public class GoodWeatherDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "good_weather";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static GoodWeatherDB goodWeatherDB;

    private SQLiteDatabase db;

    /**
     * 构造方法私有化
     *
     * @param context
     */
    private GoodWeatherDB(Context context){
        GoodWeatherOpenHelper dbHelper = new GoodWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取GoodWeatherDB实例
     *
     * @param context
     * @return 返回一个GoodWeatherDB实例
     */
    public synchronized static GoodWeatherDB getInstance(Context context){
        if(goodWeatherDB == null){
            goodWeatherDB = new GoodWeatherDB(context);
        }
        return goodWeatherDB;
    }

    /**
     * 将Province实例存入数据库
     *
     * @param province
     */
    public void saveProvince(Province province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("pronince_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    /**
     * 从数据库读取全国省份的信息
     *
     * @return
     */
    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province-code")));
            }while(cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将City实例存储到数据库
     *
     * @param city
     */
    public void saveCity(City city){
        if (city != null){
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    /**
     * 从数据库中读取某省下所有的城市信息
     *
     * @param provinceId
     * @return
     */
    public List<City> loadCities(int provinceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while(cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将County实例存入数据库
     *
     * @param county
     */
    public void saveCounty(County county){
        if (county != null){
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }

    public List<County> loadCounties(int cityId){
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id = ?", new String[]{String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            }while(cursor.moveToNext());
        }
        return list;
    }
}
