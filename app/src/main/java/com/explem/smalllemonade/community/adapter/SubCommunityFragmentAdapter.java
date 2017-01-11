package com.explem.smalllemonade.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.community.bean.CommunityContent;
import com.explem.smalllemonade.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class SubCommunityFragmentAdapter extends BaseAdapter {

    private Context context;
    private List<CommunityContent.Data> list;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SubCommunityFragmentAdapter(Context context, List<CommunityContent.Data> list) {
        this.context = context;
        this.list = list;
    }

    int lastPosition = -1;

    public void startAnimation(View view, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.trans);
            view.startAnimation(animation);
            lastPosition = position;
        }
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

        if (convertView == null) {
            convertView = CommonUtils.inflate(R.layout.subcommunityfragment_item);
        }
        TextView tv_subcommunity_title = (TextView) convertView.findViewById(R.id.tv_subcommunity_title);
        TextView tv_subcommunity_content = (TextView) convertView.findViewById(R.id.tv_subcommunity_content);
        TextView tv_subcommunity_name = (TextView) convertView.findViewById(R.id.tv_subcommunity_name);
        TextView tv_subcommunity_createTime = (TextView) convertView.findViewById(R.id.tv_subcommunity_createTime);
        TextView tv_subcommunity_replyTimes = (TextView) convertView.findViewById(R.id.tv_subcommunity_replyTimes);
        View view = convertView.findViewById(R.id.cardview_subcommunity);

        startAnimation(view,position);
        tv_subcommunity_title.setText(list.get(position).getTitle());
        tv_subcommunity_content.setText(list.get(position).getContent());
        tv_subcommunity_name.setText("我的月亮你的心");
        tv_subcommunity_replyTimes.setText(list.get(position).getReplyTimes());
//        tv_subcommunity_createTime.setText(list.get(position));

        return convertView;
    }
}
