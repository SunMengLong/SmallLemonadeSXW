package com.explem.smalllemonade;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TiaoZhuanActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiao_zhuan2);
        //三秒跳转
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(TiaoZhuanActivity.this,LoginActivity.class));
                // finish();
            }
        }, 3000);

    }
}
