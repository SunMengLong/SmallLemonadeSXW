package com.explem.smalllemonade.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.community.bean.CommunityContent;
import com.explem.smalllemonade.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/30 0030.
 */
public class MyAdapter extends BaseAdapter {


    private final List<CommunityContent.Data> list;
    private final Context context;

    public MyAdapter(Context context, List<CommunityContent.Data> dataList) {
        this.context = context;
        this.list = dataList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = CommonUtils.inflate(R.layout.category_fragment_item);

        TextView tv_toptitle = (TextView) convertView.findViewById(R.id.tv_toptitle);

        tv_toptitle.setText(list.get(position).getTitle());

        return convertView;
    }
}
