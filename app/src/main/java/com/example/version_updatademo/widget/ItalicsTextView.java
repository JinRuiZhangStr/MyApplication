package com.example.version_updatademo.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by 张金瑞 on 2017/7/27.
 */

public class ItalicsTextView extends android.support.v7.widget.AppCompatTextView {
    public ItalicsTextView(Context context) {
        super(context);
    }

    public ItalicsTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItalicsTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private Typeface mGetTypeface(){
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "kaiti.ttf");
        return typeface;
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(mGetTypeface());
    }
}
