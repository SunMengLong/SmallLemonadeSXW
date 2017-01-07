package com.explem.smalllemonade.community.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.community.bean.CommunityContent;
import com.explem.smalllemonade.view.MyListView;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class TopHolder extends RecyclerView.ViewHolder  {

    public final ListView mylistview_top;

    public TopHolder(View itemView) {
        super(itemView);
        mylistview_top = (ListView) itemView.findViewById(R.id.mylistview_top);
    }
}
