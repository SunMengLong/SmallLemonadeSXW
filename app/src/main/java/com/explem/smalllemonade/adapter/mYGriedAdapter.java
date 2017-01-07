package com.explem.smalllemonade.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by asus on 2017/1/5.
 */

public class mYGriedAdapter extends BaseAdapter {


    private final Context context;
    private final String title;

    public mYGriedAdapter(Context context, String  title, String img ) {
    this.context=context;
        this.title=title;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
