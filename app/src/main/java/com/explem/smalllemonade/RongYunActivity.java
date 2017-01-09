package com.explem.smalllemonade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.explem.smalllemonade.rongyun.User1;
import com.explem.smalllemonade.rongyun.User2;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class RongYunActivity extends AppCompatActivity implements View.OnClickListener {
    String token1="TqUnW9MhaoSHKt+U7bYZ8UYMGcrq3k/U/QhswsvQ/XjI+luG5yi89/l2L/t1ndU1OxMqRwJrtr7LLmcjMCK7aA==";
    String token2="u/jOATQ7Ny8bB4BVTSqKPNAJdAs/MZBIOidJA3Qx11DrphwygmrE0hRYjAlPsWvLgDRY/8uiBMY=";
    private ImageView title_imgback;
    private TextView title_tvtitle;
    private TextView rongyun_tv1;
    private TextView rongyun_tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rong_yun);
        //查找控件
        initView();
        //初始化數據
        initData();
        //与服务器建立连接
        webConn(token1);
        webConn(token2);
    }

    private void webConn(String token) {
        //非常重要，建立连接
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {

            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {

            }
            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    private void initData() {
        //重新賦值
        title_tvtitle.setText("消息");
        //設置監聽
        title_imgback.setOnClickListener(this);
        rongyun_tv1.setOnClickListener(this);
        rongyun_tv2.setOnClickListener(this);
    }

    private void initView() {
        title_imgback = (ImageView) findViewById(R.id.title_imgback);
        title_tvtitle = (TextView) findViewById(R.id.title_tvtitle);
        rongyun_tv1 = (TextView) findViewById(R.id.rongyun_tv1);
        rongyun_tv2 = (TextView) findViewById(R.id.rongyun_tv2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_imgback:
                //銷毀掉當前
                finish();
                break;
            case R.id.rongyun_tv1:
                //RongIM.getInstance().startPrivateChat(RongYunActivity.this,"2","2");
                startActivity(new Intent(RongYunActivity.this,User1.class));
                break;
            case R.id.rongyun_tv2:
                //RongIM.getInstance().startPrivateChat(RongYunActivity.this,"1","1");
                startActivity(new Intent(RongYunActivity.this,User2.class));
                break;
        }

    }
}
