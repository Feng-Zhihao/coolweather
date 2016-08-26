package com.fzh.app.coolweather.util;

/**
 * Created by Administrator on 2016/8/26.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
