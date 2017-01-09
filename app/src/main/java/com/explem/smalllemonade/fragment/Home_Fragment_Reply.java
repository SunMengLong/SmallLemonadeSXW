package com.explem.smalllemonade.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.adapter.Home_Fragment_Recycle_Adapter;
import com.explem.smalllemonade.view.PullBaseView;
import com.explem.smalllemonade.view.PullRecyclerView;

import java.util.ArrayList;

/**
 * Created by asus on 2016/12/28.
 */

public class Home_Fragment_Reply extends Fragment implements PullBaseView.OnHeaderRefreshListener {

    private PullRecyclerView home_fragment_recycle;
    private Home_Fragment_Recycle_Adapter home_fragment_recycle_adapter;
    private TextView tv_hide;
    private boolean flag=false;
    ArrayList<String> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_reply,null);
        home_fragment_recycle = (PullRecyclerView) v.findViewById(R.id.home_fragment_recycle);
        tv_hide = (TextView) v.findViewById(R.id.frgment555);
        //判斷如果有數據，則設置數據適配器
        if(flag) {
            home_fragment_recycle.setOnHeaderRefreshListener(this);
            home_fragment_recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            home_fragment_recycle_adapter = new Home_Fragment_Recycle_Adapter(getActivity(), list);
            home_fragment_recycle.setAdapter(home_fragment_recycle_adapter);
        }else{
            home_fragment_recycle.setVisibility(View.GONE);
            tv_hide.setVisibility(View.VISIBLE);
        }
        return v;
    }
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //  mDatas.add(0, "TEXT新增");
                home_fragment_recycle_adapter.notifyDataSetChanged();
                home_fragment_recycle.onHeaderRefreshComplete();
            }
        }, 3000);
    }
}
