package com.example.version_updatademo.moudle;

import android.app.Activity;

import com.example.version_updatademo.adapter.HorizontalScrollViewAdapter;
import com.example.version_updatademo.bean.HorizontalScrollViewInfo;
import com.example.version_updatademo.utils.HttpConstants;
import com.example.version_updatademo.utils.OkhttpUtils;
import com.example.version_updatademo.widget.MyHorizontalScollView;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by 张金瑞 on 2017/8/2.
 */

public class HorizontalScorllView_moudle {


    private static HorizontalScrollViewInfo horizontalScrollViewInfo;

    public static void initNetWork(final Activity act, final MyHorizontalScollView horizontalScollView){
        OkHttpClient client= OkhttpUtils.getInstance();
        final Request request=new Request.Builder()
                .get()
                .url(HttpConstants.horizontalscollview)
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    Gson gson=new Gson();
                    horizontalScrollViewInfo = gson.fromJson(json, HorizontalScrollViewInfo.class);
                    if (200==horizontalScrollViewInfo.getStatus()){
                        final List<String> nameList = horizontalScrollViewInfo.getData().getNameList();
                        final List<String> picList = horizontalScrollViewInfo.getData().getPicList();
                        act.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                HorizontalScrollViewAdapter adapter = new HorizontalScrollViewAdapter(act,picList,nameList);
                                horizontalScollView.initDatas(adapter);
                            }
                        });

                    }
                }
            }
        });
    }
}
