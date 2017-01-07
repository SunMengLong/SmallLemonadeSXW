package com.explem.smalllemonade;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.smalllemonade.adapter.UpnMyRecyclerViewAdapter;
import com.explem.smalllemonade.bean.WorkBean;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

public class UapdateNameAcitvity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_tvright;
    private TextView title_tvtitle, upn_tvdesc;
    private Intent intent;
    private int id;
    private AutoRelativeLayout upn_re;
    private RecyclerView upn_rcl;
    private Gson gson;
    private WorkBean workBean;
    private EditText upn_et_name;
    private TextView upn_tv_num;
    private AutoLinearLayout emotio_ll;
    private ImageView iv_double;
    private ImageView iv_single;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uapdate_name_acitvity);
        gson = new Gson();
        //找控件
        initView();
        getIntentData();
    }

    //判断跳转的类型 显示不同的页面
    public void getIntentData() {
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        switch (id) {
            //昵称
            case 0:
                title_tvtitle.setText("修改昵称");
                title_tvright.setText("完成");
                upn_tvdesc.setVisibility(View.VISIBLE);
                upn_re.setVisibility(View.VISIBLE);
                getEdtLeath();
                break;
            //情感
            case 1:
                title_tvtitle.setText("情感状况");
                title_tvright.setText("完成");
                emotio_ll.setVisibility(View.VISIBLE);
                break;
            //工作
            case 2:
                upn_rcl.setVisibility(View.VISIBLE);
                getWorkDataFromNet();
                title_tvtitle.setText("选择职业");
                title_tvright.setText("取消");
                break;
        }
    }

    //动态监听输入的字符长度
    public void getEdtLeath() {
        //设置监听
        upn_et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //正在变化
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = charSequence.length();
                upn_tv_num.setText((10 - length) + "");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    //获取职业信息
    public void getWorkDataFromNet() {
        new MyBaseData(this).getDate("http://www.yulin520.com/a2a/occupation/menu", null, 0, 0);
    }

    class MyBaseData extends BaseDate {
        private UpnMyRecyclerViewAdapter adapter;

        public MyBaseData(Context context) {
            super(context);
        }

        @Override
        public void setResultError(ShowingPage.StateType stateLoadError) {
        }

        @Override
        public void setResultData(String data) {
            workBean = gson.fromJson(data, WorkBean.class);
            List<WorkBean.DataBean> data1 = workBean.getData();
            adapter = new UpnMyRecyclerViewAdapter(UapdateNameAcitvity.this, data1);
            upn_rcl.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //设置点击事件
            adapter.setOnWorkClieckLisn(new UpnMyRecyclerViewAdapter.OnWorkClieckLisner() {
                @Override
                public void setData(String data) {
                    Toast.makeText(UapdateNameAcitvity.this, data, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    //找控件
    private void initView() {
        findViewById(R.id.title_imgback).setOnClickListener(this);
        title_tvtitle = (TextView) findViewById(R.id.title_tvtitle);
        title_tvright = (TextView) findViewById(R.id.title_tvright);
        title_tvtitle.setOnClickListener(this);
        upn_et_name = (EditText) findViewById(R.id.upn_et_name);
        upn_tv_num = (TextView) findViewById(R.id.upn_tv_num);
        upn_tvdesc = (TextView) findViewById(R.id.upn_tvdesc);
        upn_re = (AutoRelativeLayout) findViewById(R.id.upn_re);
        upn_rcl = (RecyclerView) findViewById(R.id.upn_rcl);
        emotio_ll = (AutoLinearLayout) findViewById(R.id.emotio_ll);
        upn_rcl.setLayoutManager(new LinearLayoutManager(UapdateNameAcitvity.this));
        findViewById(R.id.emotio_rela_single).setOnClickListener(this);
        findViewById(R.id.emotio_rela_double).setOnClickListener(this);
        iv_double = (ImageView) findViewById(R.id.iv_double);
        iv_single = (ImageView) findViewById(R.id.iv_single);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //后退
            case R.id.title_imgback:
                finish();
                break;
            //点击完成或取消
            case R.id.title_tvright:
                String title = title_tvtitle.getText().toString().trim();
                if (title.equals("修改昵称")) {

                } else if (title.equals("情感状态")) {

                } else if (title.equals("选择职业")) {

                }
                break;
            //热恋
            case R.id.emotio_rela_double:
                iv_double.setVisibility(View.VISIBLE);
                iv_single.setVisibility(View.GONE);
                break;
            //单身
            case R.id.emotio_rela_single:
                iv_double.setVisibility(View.GONE);
                iv_single.setVisibility(View.VISIBLE);
                break;

        }
    }
}
