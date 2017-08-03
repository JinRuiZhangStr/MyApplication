package com.example.version_updatademo.VersionUpdata;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.version_updatademo.R;
import com.example.version_updatademo.VersionUpdata.fragment.ChatFragment;
import com.example.version_updatademo.VersionUpdata.fragment.CoreFragment;
import com.example.version_updatademo.VersionUpdata.fragment.HomeFragment;
import com.example.version_updatademo.base.BaseActivity;
import com.example.version_updatademo.utils.DialogUtils;
import com.example.version_updatademo.utils.HttpConstants;
import com.example.version_updatademo.utils.PackageUtils;
import com.example.version_updatademo.widget.TabFragmentHost;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class MainActivity extends BaseActivity implements Callback, TabHost.OnTabChangeListener {

    private OkHttpClient client;
    private Request.Builder builder;
    private Call call;
    private GsonInfo.DataBean data;
    private String currentVersion;
    private TabFragmentHost tabhost;
    private int imgButton[] = {R.drawable.home_f, R.drawable.chat_f, R.drawable.core_f};
    private int imgButton2[] = {R.drawable.home_t, R.drawable.chat_t, R.drawable.core_t};
    private String textButton[] = {"home", "chat", "core"};
    private Class fragmentArray[] = {HomeFragment.class, ChatFragment.class, CoreFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkUpdata(HttpConstants.versioninfo, this);
        initView();
    }

    private void initView() {
        tabhost = (TabFragmentHost) findViewById(android.R.id.tabhost);
        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);
        tabhost.setOnTabChangedListener(this);
        for (int i = 0; i < textButton.length; i++) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(textButton[i]).setIndicator(getHomeIndicator(i));
            tabhost.addTab(tabSpec, fragmentArray[i], null);
            //点击背景
            //tabhost.getTabWidget().getChildTabViewAt(i).setBackgroundResource(R.drawable.bt_selector);
        }

    }

    private View getHomeIndicator(int i) {
        View view = View.inflate(this, R.layout.tabcontent, null);
        ImageView img = (ImageView) view.findViewById(R.id.iv);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(textButton[i]);
        if (i == 0) {
            tv.setTextColor(getResources().getColor(R.color.green));
            img.setImageResource(imgButton2[i]);
        } else {
            img.setImageResource(imgButton[i]);
        }

        return view;
    }


    private void checkUpdata(String url, Callback callback) {
        client = new OkHttpClient();
        builder = new Request.Builder();
        Request request = builder.get()
                .url(url)
                .build();
        call = client.newCall(request);
        call.enqueue(callback);
    }

    private void parseJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return;
        }
        Gson gson = new Gson();
        GsonInfo gsonInfo = gson.fromJson(json, GsonInfo.class);

        if ("200".equals(gsonInfo.getStatus())) {
            data = gsonInfo.getData();
            String verstion = data.getVersion();
            try {
                currentVersion = PackageUtils.getCurrentVersion(this);
                Log.e("TAG", "parseJson: " + currentVersion);
                if (!TextUtils.isEmpty(verstion)) {
                    if (!currentVersion.equals(verstion)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("aaaaaaaa", "run: " + data.getAppurl());
                                DialogUtils.showUpdateDialog(MainActivity.this, "版本更新", data.getInfo(), data.getAppurl());
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "已经是最新版本!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            String json = response.body().string();
            parseJson(json);
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        uptadaTab();
    }

    private void uptadaTab() {
        TabWidget tabWidget = tabhost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            View view = tabWidget.getChildTabViewAt(i);
            ImageView img = (ImageView) view.findViewById(R.id.iv);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            if (i == tabhost.getCurrentTab()) {
                tv.setTextColor(getResources().getColor(R.color.green));
                img.setImageResource(imgButton2[i]);
            } else {
                tv.setTextColor(getResources().getColor(R.color.grey));
                img.setImageResource(imgButton[i]);
            }
        }
    }
}
