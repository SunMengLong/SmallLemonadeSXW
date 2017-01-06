package com.explem.smalllemonade.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by asus on 2017/1/6.
 */

public class MyRecycleVie extends RecyclerView {
    public MyRecycleVie(Context context) {
        super(context);
        init();
    }

    public MyRecycleVie(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
    }

    public MyRecycleVie(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);

    }
}
