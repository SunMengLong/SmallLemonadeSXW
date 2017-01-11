package com.explem.smalllemonade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.bean.Home_Fragment_Love_oxygen;
import com.explem.smalllemonade.holder.Love_oxyGenHolder;

import java.util.List;

/**
 * @author Xujiajian
 * Created by asus on 2017/1/2.
 */

public class Love_oxyGenAdapter extends RecyclerView.Adapter <Love_oxyGenHolder>{
    private final Context context;
    private final List<Home_Fragment_Love_oxygen.DataBean> list;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    //初始化点击事件
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public Love_oxyGenAdapter(Context context , List<Home_Fragment_Love_oxygen.DataBean> list) {
        this.context=context;
        this.list=list;

        Log.i("lllllllll","Love_oxyGenAdapter: ......"+list.size());
    }


    @Override
    public Love_oxyGenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.home_fragment_love_oxygen, null);
        Love_oxyGenHolder holder =new Love_oxyGenHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Love_oxyGenHolder holder, int position) {
        Log.i("lllllllll",position+"onBindViewHolder: ......"+list.size());
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
//        if(list.get(position).getContentIntr().length()<=26){
//            holder.home_fragment_love_oxygen_contentInter.setText(list.get(position).getContentIntr());
//        }else{
//            holder.home_fragment_love_oxygen_contentInter.setText(list.get(position).getContentIntr().substring(0,25)+"...");
//        }
//         holder.home_fragment_love_oxygen_contentInter.setText(list.get(position).getContentIntr().substring(0,20)+"...");
        if(list.size()==1){
            list.add(list.get(0));
            list.add(list.get(0));
        }
       holder.home_fragment_love_oxygen_title.setText(list.get(position).getTitle());
        if(list.get(position).getContentIntr()!=null){
            if(list.get(position).getContentIntr().length()<=26){
                holder.home_fragment_love_oxygen_contentInter.setText(list.get(position).getContentIntr());
            }else{
                holder.home_fragment_love_oxygen_contentInter.setText(list.get(position).getContentIntr().substring(0,25)+"...");
            }
        }
        holder.home_fragment_love_oxygen_title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.home_fragment_love_oxygen_img3);
        holder.home_fragment_love_oxygen_author.setText("作者："+list.get(position).getReporterName());




    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
