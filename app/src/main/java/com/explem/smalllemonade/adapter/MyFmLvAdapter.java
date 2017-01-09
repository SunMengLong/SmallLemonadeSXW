package com.explem.smalllemonade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.bean.FmcommentBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



/**
 * Created by ${薛亚南}
 * on 2017/1/9 16：38.
 */

public class MyFmLvAdapter extends BaseAdapter {
    private Context context;
    private List<FmcommentBean.DataBean> dataList;

    public MyFmLvAdapter(Context context, List<FmcommentBean.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = LayoutInflater.from(context).inflate(R.layout.home_fragment_mylistview_item, viewGroup, false);
        CircleImageView home_fragment_lv_headImage = (CircleImageView) v.findViewById(R.id.home_fragment_lv_headImage);
        TextView home_fragment_lv_userName = (TextView) v.findViewById(R.id.home_fragment_lv_userName);
        TextView home_fragment_lv_floor = (TextView) v.findViewById(R.id.home_fragment_lv_floor);
        TextView home_fragment_lv_content = (TextView) v.findViewById(R.id.home_fragment_lv_content);
        Glide.with(context).load(dataList.get(i).getImg()).error(R.mipmap.ic_launcher).into(home_fragment_lv_headImage);
        home_fragment_lv_userName.setText(dataList.get(i).getName());
        home_fragment_lv_floor.setText(dataList.get(i).getTime() + "");
        home_fragment_lv_content.setText(dataList.get(i).getContent());
        return v;
    }

}
