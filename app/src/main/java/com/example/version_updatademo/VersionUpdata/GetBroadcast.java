package com.example.version_updatademo.VersionUpdata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.version_updatademo.utils.SharedUtils;

/**
 * Created by 张金瑞 on 2017/7/23.
 */

public class GetBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        if(Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())){
//            Toast.makeText(context, "有应用被添加", Toast.LENGTH_LONG).show();
//        }
//        else  if(Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())){
//            Toast.makeText(context, "有应用被删除", Toast.LENGTH_LONG).show();
//        }
             /*   else  if(Intent.ACTION_PACKAGE_CHANGED.equals(intent.getAction())){
                    Toast.makeText(context, "有应用被改变", Toast.LENGTH_LONG).show
                    ();
            }*/
//        else
            if(Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction())){
            Toast.makeText(context, "应用更新成功", Toast.LENGTH_LONG).show();
                SharedUtils sharedUtils=new SharedUtils();
                sharedUtils.saveShared_int("updata_status",0,context);
        }
               /* else  if(Intent.ACTION_PACKAGE_RESTARTED.equals(intent.getAction())){
                    Toast.makeText(context, "有应用被重启", Toast.LENGTH_LONG).show();
            }*/
              /*  else  if(Intent.ACTION_PACKAGE_INSTALL.equals(intent.getAction())){
                    Toast.makeText(context, "有应用被安装", Toast.LENGTH_LONG).show();
            }*/
    }


}
