package com.explem.smalllemonade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.bean.LoveCommunityBean;
import com.explem.smalllemonade.holder.LoveRecycleHolder;

import java.util.List;

/**
 * @author XuJiaJian
 */

public class LoveRecycleAdapter extends RecyclerView.Adapter<LoveRecycleHolder> {
    private final Context context;
    private final List<LoveCommunityBean.DataBean> list;

    public LoveRecycleAdapter(Context context, List<LoveCommunityBean.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    //点击事件
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    //初始化点击事件
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public LoveRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.home_fragment_love_recycle_item,null);
        LoveRecycleHolder holder=new LoveRecycleHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final LoveRecycleHolder holder, int position) {
        holder.home_fragment__love_recycle_tv.setText(list.get(position).getTitle());
        holder.  home_fragment__love_recycle_img.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(list.get(position).getImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.home_fragment__love_recycle_img);

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
