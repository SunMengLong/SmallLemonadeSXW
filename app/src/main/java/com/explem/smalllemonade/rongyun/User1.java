package com.explem.smalllemonade.rongyun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.explem.smalllemonade.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class User1 extends AppCompatActivity {
    String token1="TqUnW9MhaoSHKt+U7bYZ8UYMGcrq3k/U/QhswsvQ/XjI+luG5yi89/l2L/t1ndU1OxMqRwJrtr7LLmcjMCK7aA==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);
        //非常重要，建立连接
        RongIM.connect(token1, new RongIMClient.ConnectCallback() {
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
        RongIM.getInstance().startPrivateChat(User1.this,"2","2");
        finish();
    }

    public void but1(View view) {
        RongIM.getInstance().startPrivateChat(User1.this,"2","2");
    }
}
