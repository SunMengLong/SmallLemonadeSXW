package com.explem.smalllemonade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class Lenmmon_Girl_Gift extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenmmon__girl__gift);
        WebView activity_lenmmon__girl__gift_web = (WebView) findViewById(R.id.activity_lenmmon__girl__gift_web);
        //赋值
        activity_lenmmon__girl__gift_web.loadUrl("http://www.xiaoningle.com/a2a/h/i/app/gift_list");
    }
}
