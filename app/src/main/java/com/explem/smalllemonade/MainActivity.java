package com.explem.smalllemonade;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.CheckBox;

import com.explem.smalllemonade.base.BaseActivity;
import com.explem.smalllemonade.fragment.CommunityFragment;
import com.explem.smalllemonade.fragment.HomeFragment;
import com.explem.smalllemonade.fragment.MineFragment;
import com.explem.smalllemonade.view.NoScollViewPager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private NoScollViewPager activity_vp;
    private CheckBox home_fragment_check;
    private CheckBox community_fragment_check;
    private CheckBox mine_fragment_check;
    //设置TAG值
    public static final int HOMETAG=0;
    public static final int COMMTAG=1;
    public static final int MINETAG=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //设置数据
        setData();
        //加载图片
        //Glide.with(WaterWallActivity.this).load(Images.imageUrls[position]).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.iv_water_item);
    }

    private void setData() {
        activity_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment ff=null;
                switch (position) {
                    case 0:
                        ff=new HomeFragment();
                        break;
                    case 1:
                        ff=new CommunityFragment();
                        break;
                    case 2:
                        ff=new MineFragment();
                        break;
                }
                return ff;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    private void initView() {
        activity_vp = (NoScollViewPager) findViewById(R.id.activity_vp);
        //下方的三个按钮
        home_fragment_check = (CheckBox) findViewById(R.id.home_fragment_check);
        community_fragment_check = (CheckBox) findViewById(R.id.community_fragment_check);
        mine_fragment_check = (CheckBox) findViewById(R.id.mine_fragment_check);
        //监听
        home_fragment_check.setOnClickListener(this);
        community_fragment_check.setOnClickListener(this);
        mine_fragment_check.setOnClickListener(this);
        //设置唯一的TAG值
        home_fragment_check.setTag(HOMETAG+"");
        community_fragment_check.setTag(COMMTAG+"");
        mine_fragment_check.setTag(MINETAG+"");
    }

    @Override
    public void onClick(View view) {
        String tag= (String) view.getTag();
        switch (Integer.parseInt(tag)) {
            case HOMETAG:
                setChecked(home_fragment_check,community_fragment_check,mine_fragment_check);
                activity_vp.setCurrentItem(0);
                break;
            case COMMTAG:
                setChecked(community_fragment_check,home_fragment_check,mine_fragment_check);
                activity_vp.setCurrentItem(1);
                break;
            case MINETAG:
                setChecked(mine_fragment_check,home_fragment_check,community_fragment_check);
                activity_vp.setCurrentItem(2);
                break;
            default:
                break;
        }
    }
    //设置选中
    public void setChecked(CheckBox check1,CheckBox check2,CheckBox check3){
        check1.setChecked(true);
        check2.setChecked(false);
        check3.setChecked(false);
    }
}
