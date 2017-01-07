package com.explem.smalllemonade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;


import com.explem.smalllemonade.R;
import com.explem.smalllemonade.bean.WorkBean;
import com.explem.smalllemonade.holder.UpnMyHolder;
;

import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by ${薛亚南}
 * on 2017/1/1 21：41.
 */

public class UpnMyRecyclerViewAdapter extends RecyclerView.Adapter<UpnMyHolder> {
    private Context context;
    private List<WorkBean.DataBean> data;
    private OnWorkClieckLisner onWorkClieckLisner;
    private int lastPosition = -1;

    public UpnMyRecyclerViewAdapter(Context context, List<WorkBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public UpnMyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_work, parent, false);
        UpnMyHolder upnMyHolder = new UpnMyHolder(view);
        return upnMyHolder;
    }

    @Override
    public void onBindViewHolder(UpnMyHolder holder, int position) {
        startAnimation(holder.list_item,position);
        holder.tv_work_title.setText(data.get(position).getTitle());
        final List<WorkBean.DataBean.ChildrenBean> children = data.get(position).getChildren();
        holder.lv_work.setAdapter(new UpnLvAdapter(context, children));
            holder.lv_work.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    UpnMyRecyclerViewAdapter.this.onWorkClieckLisner.setData(children.get(i).getTitle());
                }
            });
    }
    @Override
    public void onViewDetachedFromWindow(UpnMyHolder holder) {
        super.onViewDetachedFromWindow(holder);
           holder.tv_work_title.clearAnimation();
         holder.lv_work.clearAnimation();
    }

    public void startAnimation(View view, int position) {
        if (position > lastPosition) {
            TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(context, R.anim.item_translate);
            view.startAnimation(translateAnimation);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnWorkClieckLisn(OnWorkClieckLisner onWorkClieckLisner){
          this.onWorkClieckLisner = onWorkClieckLisner;
    }

    //定义接口
    public interface  OnWorkClieckLisner{
         void setData(String data);
    }
}
