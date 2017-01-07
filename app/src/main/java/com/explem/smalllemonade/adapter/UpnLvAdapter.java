package com.explem.smalllemonade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.bean.WorkBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by ${薛亚南}
 * on 2017/1/2 20：00.
 */

public class UpnLvAdapter extends BaseAdapter {
    private Context context;
    private List<WorkBean.DataBean.ChildrenBean> dataList;
    private int lastPosition = -1;

    public UpnLvAdapter(Context context, List<WorkBean.DataBean.ChildrenBean> dataList) {
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
        ViewHodler viewHodler = null;
        if (view == null) {
            //   view = View.inflate(context, R.layout.item_lv_tv, null);
            viewHodler = new ViewHodler();
            view = LayoutInflater.from(context).inflate(R.layout.item_lv_tv, viewGroup, false);
            view.setTag(viewHodler);
            viewHodler.tv_work = (TextView) view.findViewById(R.id.tv_work);
            AutoUtils.autoSize(view);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.tv_work.setText(dataList.get(i).getTitle());
        return view;
    }

    class ViewHodler {
        TextView tv_work;
    }
}
