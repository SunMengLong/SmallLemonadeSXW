package com.explem.smalllemonade.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.view.MyListView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by ${薛亚南}
 * on 2017/1/1 21：42.
 */

public class UpnMyHolder extends RecyclerView.ViewHolder {

    public TextView tv_work_title;
    public MyListView lv_work;
     public  AutoLinearLayout list_item;

    public UpnMyHolder(View itemView) {
        super(itemView);
        AutoUtils.autoSize(itemView);
        list_item = (AutoLinearLayout) itemView.findViewById(R.id.list_item);
        tv_work_title = (TextView) itemView.findViewById(R.id.tv_work_title);
        lv_work = (MyListView) itemView.findViewById(R.id.lv_work);
    }
}
