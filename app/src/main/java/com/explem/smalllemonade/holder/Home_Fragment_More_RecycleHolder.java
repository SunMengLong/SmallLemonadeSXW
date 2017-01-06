package com.explem.smalllemonade.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.explem.smalllemonade.R;

/**
 * Created by asus on 2017/1/5.
 */

public class Home_Fragment_More_RecycleHolder extends RecyclerView.ViewHolder {

    public final ImageView look_more_recycle_img;
    public final TextView look_more_recycle_title;
    public final RelativeLayout home_fragment_rls;


    public Home_Fragment_More_RecycleHolder(View itemView) {
        super(itemView);
        look_more_recycle_img = (ImageView) itemView.findViewById(R.id.look_more_recycle_img);
        look_more_recycle_title = (TextView) itemView.findViewById(R.id.look_more_recycle_title);
        home_fragment_rls = (RelativeLayout) itemView.findViewById(R.id.home_fragment_rls);
    }
}
