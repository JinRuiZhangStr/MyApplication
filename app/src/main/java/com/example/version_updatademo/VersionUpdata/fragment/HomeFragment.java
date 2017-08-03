package com.example.version_updatademo.VersionUpdata.fragment;


import android.view.LayoutInflater;
import android.view.View;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.version_updatademo.Guide.GuideInfo;
import com.example.version_updatademo.R;
import com.example.version_updatademo.adapter.HorizontalScrollViewAdapter;
import com.example.version_updatademo.base.BaseFragment;
import com.example.version_updatademo.bean.HorizontalScrollViewInfo;
import com.example.version_updatademo.moudle.HorizontalScorllView_moudle;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 张金瑞 on 2017/7/27.
 */

public class HomeFragment extends BaseFragment implements Callback{

    private SliderLayout slider;
    private PagerIndicator custom_indicator;
    private List<String> guidepic;
    private List<Integer> lists=new ArrayList<>(Arrays.asList(R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher));
    private MyHorizontalScollView myScollView;
    private HorizontalScrollViewAdapter adapter;
    private HorizontalScrollViewInfo horizontalScrollViewInfo;

    @Override
    public View initView(LayoutInflater inflater) {
        View view=inflater.inflate(R.layout.home_fragment,null,false);
        findByid(view);
        return view;
    }

    private void findByid(View v) {
        slider = (SliderLayout) v.findViewById(R.id.slider);
        custom_indicator = (PagerIndicator) v.findViewById(R.id.custom_indicator);

        myScollView = (MyHorizontalScollView) v.findViewById(R.id.myhorizontalScollView);
        HorizontalScorllView_moudle.initNetWork(getActivity(),myScollView);
    }

    @Override
    public void initData() {
        OkHttpClient client=OkhttpUtils.getInstance();
        Request request=new Request.Builder()
                .get()
                .url(HttpConstants.guide)
                .build();
        Call call=client.newCall(request);
        call.enqueue(this);


    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()){
            String json = response.body().string();
            Gson gson=new Gson();
            GuideInfo guideInfo = gson.fromJson(json, GuideInfo.class);
            if (guideInfo!=null){
                if (200==guideInfo.getStatus()){
                    guidepic = guideInfo.getData().getGuidepic();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updataface();
                        }
                    });
                }
            }
        }
    }

    private void updataface() {
        for (int i=0;i<guidepic.size();i++){
            TextSliderView tsv=new TextSliderView(getActivity());
            tsv.image(guidepic.get(i));
            slider.addSlider(tsv);
        }
//        slider.setCustomAnimation(new DescriptionAnimation());
//        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setDuration(3000);
        //      sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomIndicator(custom_indicator);

    }
}
