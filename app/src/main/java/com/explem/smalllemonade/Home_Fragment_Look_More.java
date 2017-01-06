package com.explem.smalllemonade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.explem.smalllemonade.adapter.Home_Fragment_More_RecycleAdapter;
import com.explem.smalllemonade.bean.Home_Fragment_Love_oxygen;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

/**
 * 点击加载更多
 * XuJiaJian
 */
public class Home_Fragment_Look_More extends AppCompatActivity implements View.OnClickListener, SpringView.OnFreshListener {
    //判断请求数据的方式
    private static int GETDATE_MORE=0;
    private static int GETDATE_FRUSH=1;
    private static int GETDATE_FIRST=2;


        private  int page=1;
    private RecyclerView home_fragment_lun_recycle;
    private List<Home_Fragment_Love_oxygen.DataBean> list;
    private SwipeRefreshLayout swip;
    private SpringView home_spring;
    public static String path_oxygen = "http://www.yulin520.com/a2a/news/sd/list";
    public static String args_oxygen = "sign=FF249FC05B920D994BE888EBD6F68133&ts=1482905506&pageSize=6&page=";
    private Home_Fragment_Love_oxygen home_fragment_love_oxygen;
    private  List<Home_Fragment_Love_oxygen.DataBean> list2=new ArrayList<>();
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            home_fragment_love_oxygen = (Home_Fragment_Love_oxygen) msg.obj;
            if(msg.arg1!=GETDATE_MORE){
                home_fragment_more_recycleAdapter = new Home_Fragment_More_RecycleAdapter(Home_Fragment_Look_More.this, home_fragment_love_oxygen.getData());
                home_fragment_lun_recycle.setAdapter(home_fragment_more_recycleAdapter);
            }else{
                list2.addAll(0,home_fragment_love_oxygen.getData());
                home_fragment_more_recycleAdapter = new Home_Fragment_More_RecycleAdapter(Home_Fragment_Look_More.this,list2);
                home_fragment_lun_recycle.setAdapter(home_fragment_more_recycleAdapter);
              //  home_fragment_more_recycleAdapter.notifyDataSetChanged();
            }
        }
    };
    private Home_Fragment_More_RecycleAdapter home_fragment_more_recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__fragment__look__more);
        Intent intent = getIntent();
        initView(list);
    }
    private void initView(List<Home_Fragment_Love_oxygen.DataBean> list) {
        home_fragment_lun_recycle = (RecyclerView) findViewById(R.id.home_fragment_lun_recycle);
        home_spring = (SpringView) findViewById(R.id.home_spring);
        home_fragment_lun_recycle.setLayoutManager(new LinearLayoutManager(this));
        ImageView tuicu = (ImageView) findViewById(R.id.tuicu);
        getNet(path_oxygen,args_oxygen,GETDATE_FIRST,page);
        tuicu.setOnClickListener(this);
        home_spring.setHeader(new DefaultHeader(this));
        home_spring.setFooter(new DefaultFooter(this));
        home_spring.setListener(this);
    }
    public  void  getNet(String path, String args, final int tag , final int page){
        new BaseDate(Home_Fragment_Look_More.this){
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                Gson gson =new Gson();
                    home_fragment_love_oxygen = gson.fromJson(data, Home_Fragment_Love_oxygen.class);
                    Message msg5 = new Message();
                    msg5.obj = home_fragment_love_oxygen;
                    msg5.arg1 = tag;
                    handler.sendMessage(msg5);
            }
        }.getDate(path,args+page,1,BaseDate.NOTIME);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tuicu:
                finish();
            break;
        }
    }
    @Override
    public void onRefresh() {
        //刷新
        getNet(path_oxygen,args_oxygen,GETDATE_FRUSH,1);
    }
    @Override
    public void onLoadmore() {
        page++;
        getNet(path_oxygen,args_oxygen,GETDATE_MORE,page);
        home_fragment_more_recycleAdapter.notifyDataSetChanged();
    };
}
