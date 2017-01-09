package com.explem.smalllemonade.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.RongYunActivity;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by asus on 2016/12/28.
 */

public class Home_Fragment_Talk extends Fragment implements View.OnClickListener {

    private View v;
    private AutoLinearLayout home_talk_lin1;
    private AutoLinearLayout home_talk_lin2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_talk,null);
        //初始化佈局
        initView();
        return v;
    }

    private void initView() {
        home_talk_lin1 = (AutoLinearLayout) v.findViewById(R.id.home_talk_lin1);
        home_talk_lin2 = (AutoLinearLayout) v.findViewById(R.id.home_talk_lin2);
        //設置點擊事件
        home_talk_lin1.setOnClickListener(this);
        home_talk_lin2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_talk_lin1:
                //跳轉到聊天界面
                startActivity(new Intent(getActivity(), RongYunActivity.class));
                break;
            case R.id.home_talk_lin2:
                Toast.makeText(getActivity(), "系统暂无消息", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
