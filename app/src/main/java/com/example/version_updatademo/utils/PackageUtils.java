package com.example.version_updatademo.utils;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * Created by 张金瑞 on 2017/7/21.
 */

public class PackageUtils {
    public static String getCurrentVersion(Activity activity)throws PackageManager.NameNotFoundException{
        PackageManager pm=activity.getPackageManager();

            PackageInfo info=pm.getPackageInfo(activity.getPackageName(),0);
            String versionName=info.versionName;
            if (TextUtils.isEmpty(versionName)){
                return "";
            }
            return versionName;

    }

}
