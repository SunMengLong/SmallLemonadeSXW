package com.explem.smalllemonade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;


import com.explem.smalllemonade.adapter.MyViewPagerAdaPter;
import com.explem.smalllemonade.base.BaseActivity;

import java.util.ArrayList;

public class DaoHangActivity extends BaseActivity {

    private ViewPager vp;
    private ArrayList<Integer> al;
    private boolean isFirstIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_hang);
        //找控件
        vp=(ViewPager)findViewById(R.id.vp);
        //初始化数据
        initData();
        //判断是否是第一次进入
        SharedPreferences preferences = getSharedPreferences("first_open",MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("is_first_open", true);
        if (isFirstIn) {
        //第一次进入时先把first_open置为false以便后来进入时进行判断，除此之外，还可以写入第一次进入时苏要执行的动作
            preferences = getSharedPreferences("first_open", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("is_first_open", false);
            editor.commit();
            MyViewPagerAdaPter   myViewPager=new MyViewPagerAdaPter(DaoHangActivity.this,al);
            vp.setAdapter(myViewPager);

        } else {
            //不是第一次进入时所要做的动作
            Intent intent=new Intent(DaoHangActivity.this,TiaoZhuanActivity.class);
            startActivity(intent);
        }
    }
    private void initData() {
        al=new ArrayList<>();
        al.add(R.mipmap.introductory1_xhdpi);
        al.add(R.mipmap.introductory2_xhdpi);
        al.add(R.mipmap.introductory3_xhdpi);
        al.add(R.mipmap.introductory4_xhdpi);
    }
}
