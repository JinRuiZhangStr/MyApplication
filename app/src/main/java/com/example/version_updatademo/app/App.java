package com.example.version_updatademo.app;

import android.app.Application;

import com.example.version_updatademo.utils.SharedUtils;

/**
 * Created by 张金瑞 on 2017/7/26.
 */

public class App extends Application {

    private SharedUtils sharedUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedUtils = new SharedUtils();
    }
}
