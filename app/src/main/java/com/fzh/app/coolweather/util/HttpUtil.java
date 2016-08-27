package com.fzh.app.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/26.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("Get");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String response = "";
                    while ((response = bufferedReader.readLine()) != null){
                        builder.append(response);
                    }
                    if (listener != null){
                        listener.onFinish(builder.toString());
                    }
                } catch (Exception e) {
                    if (listener != null){
                        listener.onError(e);
                    }
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
