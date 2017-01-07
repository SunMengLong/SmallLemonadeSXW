package com.explem.smalllemonade.community.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.community.bean.CommunityContent;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class ContentHolder extends RecyclerView.ViewHolder  {

    public final TextView tv_subcommunity_title;
    public final TextView tv_subcommunity_content;
    public final TextView tv_subcommunity_name;
    public final TextView tv_subcommunity_createTime;
    public final TextView tv_subcommunity_replyTimes;

    public ContentHolder(View itemView) {
        super(itemView);
        tv_subcommunity_title = (TextView) itemView.findViewById(R.id.tv_subcommunity_title);
        tv_subcommunity_content = (TextView) itemView.findViewById(R.id.tv_subcommunity_content);
        tv_subcommunity_name = (TextView) itemView.findViewById(R.id.tv_subcommunity_name);
        tv_subcommunity_createTime = (TextView) itemView.findViewById(R.id.tv_subcommunity_createTime);
        tv_subcommunity_replyTimes = (TextView) itemView.findViewById(R.id.tv_subcommunity_replyTimes);
    }

}
