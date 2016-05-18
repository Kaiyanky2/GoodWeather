package com.goodweather.app.util;

/**
 * Created by wangkaiyan on 16/5/18.
 */
public interface HttpCallBackListener {

    void onFinish(String response);

    void onError(Exception e);
}
