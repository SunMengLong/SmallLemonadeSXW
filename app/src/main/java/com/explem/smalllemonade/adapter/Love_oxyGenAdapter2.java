package com.explem.smalllemonade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.bean.Home_Fragment_Love_oxygen;
import com.explem.smalllemonade.holder.Love_oxyGenHolder2;

import java.util.List;

/**
 * Created by asus on 2017/1/2.
 */

public class Love_oxyGenAdapter2 extends RecyclerView.Adapter<Love_oxyGenHolder2> {
    private final Context context;
    private final List<Home_Fragment_Love_oxygen.DataBean> list;

    public Love_oxyGenAdapter2(Context context, List<Home_Fragment_Love_oxygen.DataBean> list) {
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
    public Love_oxyGenHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.home_fragment_love_oxygen, null);
        Love_oxyGenHolder2 holder =new Love_oxyGenHolder2(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Love_oxyGenHolder2 holder, int position) {
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
        if(list.get(position).getContentIntr().length()>26){
            holder.home_fragment_love_oxygen_contentInter.setText(list.get(position).getContentIntr().substring(0,25)+"...");
        }
            holder.home_fragment_love_oxygen_contentInter.setText(list.get(position).getContentIntr());
        holder.home_fragment_love_oxygen_title2.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.home_fragment_love_oxygen_img8);
        holder.home_fragment_love_oxygen_author2.setText("作者："+list.get(position).getReporterName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
