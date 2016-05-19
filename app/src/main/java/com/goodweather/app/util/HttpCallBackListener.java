package com.goodweather.app.util;

/**
 * 回调服务返回的结果所用到的接口
 *
 * Created by wangkaiyan on 16/5/18.
 */
public interface HttpCallBackListener {

    void onFinish(String response);

    void onError(Exception e);
}
