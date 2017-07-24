package com.example.version_updatademo.VersionUpdata;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by 张金瑞 on 2017/7/21.
 */

public class DialogUtils {
    public static void showUpdateDialog(final Activity activity, String title, String mes, final String url){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(mes);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadAndInstall(activity,url);
            }
        });
        AlertDialog dialog= builder.create();
        dialog.show();

    }

    private static void downloadAndInstall(Activity activity, String url) {
        DownLoader.downLoadAndInstallApk(activity,url);
    }
}
