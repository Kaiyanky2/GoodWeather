package com.goodweather.app.util;

import android.text.TextUtils;

import com.goodweather.app.db.GoodWeatherDB;
import com.goodweather.app.model.City;
import com.goodweather.app.model.County;
import com.goodweather.app.model.Province;

/**
 * Created by wangkaiyan on 16/5/19.
 */
public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     *
     * @param goodWeatherDB
     * @param response
     * @return
     */
    public synchronized static boolean handleProvincesResponse(GoodWeatherDB goodWeatherDB, String response){
        if (!TextUtils.isEmpty(response)){
            /*
            开始解析数据
             */
            String[] allProvinces = response.split(","); // 先按逗号分隔
            if (allProvinces != null && allProvinces.length > 0){
                for (String p : allProvinces){
                    String[] array =  p.split("\\|"); // 再按单竖线分隔
                    //将解析来的数据设置在实体类中
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将数据存储在province表中
                    goodWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     *
     * @param goodWeatherDB
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCitiesResponse(GoodWeatherDB goodWeatherDB, String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0){
                for (String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    goodWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     *
     * @param goodWeatherDB
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountiesResponse(GoodWeatherDB goodWeatherDB, String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0){
                for (String c : allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    goodWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
