package com.explem.smalllemonade.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.bean.Love_shequ;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by asus on 2017/1/4.
 */

public class MyListViewAdapter extends BaseAdapter {
    private  Context context;
    private List<Love_shequ.DataBean> list;

    public MyListViewAdapter(Context context , List<Love_shequ.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        View v = View.inflate(context, R.layout.home_fragment_mylistview_item, null);
        CircleImageView home_fragment_lv_headImage = (CircleImageView) v.findViewById(R.id.home_fragment_lv_headImage);
        TextView home_fragment_lv_userName = (TextView) v.findViewById(R.id.home_fragment_lv_userName);
        TextView home_fragment_lv_floor = (TextView) v.findViewById(R.id.home_fragment_lv_floor);
        TextView home_fragment_lv_content = (TextView) v.findViewById(R.id.home_fragment_lv_content);
        Glide.with(context).load(list.get(i).getHeadImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(home_fragment_lv_headImage);
        home_fragment_lv_userName.setText(list.get(i).getUserName());
        home_fragment_lv_floor.setText(list.get(i).getFloor());
        home_fragment_lv_content.setText(list.get(i).getContent());
       return v;
    }
}
