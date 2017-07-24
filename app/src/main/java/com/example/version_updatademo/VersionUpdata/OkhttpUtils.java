package com.example.version_updatademo.VersionUpdata;


import com.squareup.okhttp.OkHttpClient;

/**
 * Created by 张金瑞 on 2017/7/21.
 */

public class OkhttpUtils {
    private static OkhttpUtils okhttpUtils=new OkhttpUtils();
    private static OkHttpClient client;

    private OkhttpUtils(){
        client=new OkHttpClient();
    }
    public static OkHttpClient getInstance(){
        return client;
    }

}
