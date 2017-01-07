package com.explem.smalllemonade.community;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.base.BaseActivity;
import com.explem.smalllemonade.community.fragment.CategoryFragment;

public class CategoryDetailActivity extends BaseActivity implements View.OnClickListener {

    public FrameLayout framelayout_community_category;
    public String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_category);

        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");
        Log.i("hahaha","oncreate里的"+flag);
        initView();
    }

    private void initView() {

        findViewById(R.id.tv_back).setOnClickListener(this);
        framelayout_community_category = (FrameLayout) findViewById(R.id.framelayout_community_category);
        setTitle();
        findViewById(R.id.tv_community_ditail_edit).setOnClickListener(this);
        findViewById(R.id.tv_community_ditail_bar).setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CategoryFragment categoryFragment = CategoryFragment.getFragment(flag);
        transaction.add(R.id.framelayout_community_category, categoryFragment, "first").commit();
    }

    private void setTitle() {
        TextView tv_community_ditail_title = (TextView) findViewById(R.id.tv_community_ditail_title);
        switch (flag) {
            case "first":
                tv_community_ditail_title.setText("你的月亮我的心");
                break;
            case "second":
                tv_community_ditail_title.setText("恋爱羞羞事");
                break;

            case "third":
                tv_community_ditail_title.setText("约会必杀技");
                break;

            case "fourth":
                tv_community_ditail_title.setText("主要看颜值");
                break;

            case "five":
                tv_community_ditail_title.setText("恋爱直播间");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();

                break;

            case R.id.tv_community_ditail_edit:

                break;

            case R.id.tv_community_ditail_bar:

                break;
        }
    }
}
