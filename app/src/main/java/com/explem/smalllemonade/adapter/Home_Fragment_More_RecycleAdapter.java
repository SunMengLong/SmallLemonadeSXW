package com.explem.smalllemonade.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.bean.Home_Fragment_Love_oxygen;
import com.explem.smalllemonade.holder.Home_Fragment_More_RecycleHolder;

import java.util.List;

/**
 * Created by asus on 2017/1/5.
 */

public class Home_Fragment_More_RecycleAdapter extends RecyclerView.Adapter<Home_Fragment_More_RecycleHolder> {
    private final Context context;
    private final List<Home_Fragment_Love_oxygen.DataBean> list;
    int lastPosition = -1;

    public void startAnimation(View view, int position) {
  /*      if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.translate);
            view.startAnimation(animation);
            lastPosition = position;
        }*/
    }

    public Home_Fragment_More_RecycleAdapter(Context context, List<Home_Fragment_Love_oxygen.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Home_Fragment_More_RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vv = View.inflate(context, R.layout.look_more_recycle_item, null);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(vv, View.TRANSLATION_Y, 500,300,100, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        Home_Fragment_More_RecycleHolder holders = new Home_Fragment_More_RecycleHolder(vv);
        return holders;
    }

    @Override
    public void onBindViewHolder(Home_Fragment_More_RecycleHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.look_more_recycle_img);
        holder.look_more_recycle_img.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.look_more_recycle_title.setText(list.get(position).getTitle());
      //  startAnimation(holder.home_fragment_rls, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
