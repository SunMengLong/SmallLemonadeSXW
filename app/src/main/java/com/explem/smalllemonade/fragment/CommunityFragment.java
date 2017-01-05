package com.explem.smalllemonade.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.base.BaseFragment;
import com.explem.smalllemonade.community.fragment.SubCommunityFragment_Category;
import com.explem.smalllemonade.community.fragment.SubCommunityFragment_Some;
import com.explem.smalllemonade.utils.CommonUtils;
import com.explem.smalllemonade.view.ShowingPage;

/**
 * Created by Pooh on 2016/12/27.
 */

public class CommunityFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    public RadioGroup rg_community_header;
    public CommunityPagerAdapter communityPagerAdapter;
    public ViewPager vp_community_content;
    public View view;
    private String tag_str;

    @Override
    protected void onload() {
        //设置布局
        CommunityFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {
        view = CommonUtils.inflate(R.layout.fragment_community);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //上拉刷新下拉加载
//        SpringView sv_community_content = (SpringView) getActivity().findViewById(R.id.sv_community_content);

        //头部
        rg_community_header = (RadioGroup) view.findViewById(R.id.rg_community_header);

        vp_community_content = (ViewPager) view.findViewById(R.id.vp_community_content);
        //上拉刷新下拉加载的设置
//        sv_community_content.setHeader(new DefaultHeader(getActivity()));
//        sv_community_content.setListener(this);

        //对头部进行监听
        rg_community_header.setOnCheckedChangeListener(this);
        communityPagerAdapter = new CommunityPagerAdapter(getActivity().getSupportFragmentManager());
//
        vp_community_content.setAdapter(communityPagerAdapter);

        vp_community_content.setOnPageChangeListener(this);


        //对头部的监听
        for (int i = 0; i < rg_community_header.getChildCount(); i++) {
            RadioButton rb= (RadioButton) rg_community_header.getChildAt(i);
            //添加监听
            rb.setTag(i+"");
            rb.setOnClickListener(this);
        }

    }
    //对头部进行监听
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //vp_community_content.setCurrentItem(checkedId);
        Toast.makeText(getActivity(), "...."+checkedId, Toast.LENGTH_SHORT).show();
    }



//viewpager滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置头部的字体颜色
        for (int i = 0; i < 3; i++) {
            RadioButton childAt = (RadioButton) rg_community_header.getChildAt(i);
            if (i == position){
                childAt.setTextColor(getResources().getColor(R.color.colorYellow));
            }else{
                childAt.setTextColor(getResources().getColor(R.color.colorgray));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        tag_str = (String) view.getTag();
        int tag_int=Integer.parseInt(tag_str);
        vp_community_content.setCurrentItem(tag_int);
    }


    class CommunityPagerAdapter extends FragmentPagerAdapter{

        public CommunityPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                SubCommunityFragment_Category subCommunityFragment_category = new SubCommunityFragment_Category();
                return subCommunityFragment_category;
            }else{
                SubCommunityFragment_Some subCommunityFragment_some = new SubCommunityFragment_Some();
                return  subCommunityFragment_some;
            }
        }
        @Override
        public int getCount() {
            return 3;
        }
    }
}
