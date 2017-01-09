package com.explem.smalllemonade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.explem.smalllemonade.base.BaseActivity;
import com.explem.smalllemonade.fragment.BaseInfoFragment;
import com.explem.smalllemonade.fragment.FeedFragment;
import com.explem.smalllemonade.fragment.MinePostFragment;
import com.explem.smalllemonade.fragment.SetingFragment;

//import static com.explem.smalllemonade.R.id.title_imgRight;
import static com.explem.smalllemonade.R.id.title_imgback;

/**
 * Created by ${薛亚南}
 * on 2016/12/30 09：52.
 */

public class MineJumpActivity extends BaseActivity implements View.OnClickListener {
    private TextView title_tvtitle, title_tvright;
    private BaseInfoFragment baseInfoFragment;
    private SetingFragment setingFragment;
    private MinePostFragment minePostFragmnet;
    private FeedFragment feedFragment;
    private ImageView title_imgRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minejump);
        //找控件
        initView();
        addFragment();
        getIntentId();

    }

    private void addFragment() {
        baseInfoFragment = new BaseInfoFragment();
        setingFragment = new SetingFragment();
        minePostFragmnet = new MinePostFragment();
        feedFragment = new FeedFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mjactivity_framl, baseInfoFragment).
                add(R.id.mjactivity_framl, setingFragment).add(R.id.mjactivity_framl, feedFragment).
                add(R.id.mjactivity_framl, minePostFragmnet).hide(baseInfoFragment).hide(setingFragment)
                .hide(setingFragment).hide(feedFragment)
                .commit();
    }

    //获取传入的id值 根据值显示不同的页面
    public void getIntentId() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        switch (id) {
            case 0:
                title_tvtitle.setText("基本信息");
                title_tvright.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().show(baseInfoFragment).hide(minePostFragmnet)
                        .hide(setingFragment).hide(feedFragment).commit();
                break;
            case 1:
                title_tvtitle.setText("我的帖子");
                title_tvright.setVisibility(View.GONE);
                title_imgRight.setVisibility(View.VISIBLE);
             title_imgRight.setImageResource(R.mipmap.forum_post_btn);
                getSupportFragmentManager().beginTransaction().hide(baseInfoFragment).show(minePostFragmnet)
                        .hide(setingFragment).hide(feedFragment).commit();
                break;
            case 2:
                title_tvtitle.setText("意见反馈");
                title_tvright.setText("提交");
                title_tvright.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().hide(baseInfoFragment).hide(minePostFragmnet)
                        .hide(setingFragment).show(feedFragment).commit();
                break;
            case 3:
                title_tvtitle.setText("设置");
               title_tvright.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().hide(baseInfoFragment).hide(minePostFragmnet)
                        .show(setingFragment).hide(feedFragment).commit();
                break;

        }
    }

    private void initView() {
        findViewById(title_imgback).setOnClickListener(this);
        //标题
        title_tvtitle = (TextView) findViewById(R.id.title_tvtitle);
        //右侧
        title_tvright = (TextView) findViewById(R.id.title_tvright);
       title_imgRight = (ImageView) findViewById(R.id.title_imgRight);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case title_imgback:
                finish();
                break;
        }

    }
}
