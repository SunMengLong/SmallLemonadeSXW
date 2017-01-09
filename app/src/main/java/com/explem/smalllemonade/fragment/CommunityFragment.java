package com.explem.smalllemonade.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.base.BaseFragment;
import com.explem.smalllemonade.community.fragment.SubCommunityFragment_Category;
import com.explem.smalllemonade.community.fragment.SubCommunityFragment_Some;
import com.explem.smalllemonade.utils.CommonUtils;
import com.explem.smalllemonade.view.Home_Fragemnt_Note;
import com.explem.smalllemonade.view.ShowingPage;
import com.liaoinstan.springview.widget.SpringView;

import static android.R.attr.fragment;

/**
 * Created by Pooh on 2016/12/27.
 */

public class CommunityFragment extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    public RadioGroup rg_community_header;
    public CommunityPagerAdapter communityPagerAdapter;
    public ViewPager vp_community_content;
    public View view;
    public ImageView iv_fragment_community_note;

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
        //头部
        rg_community_header = (RadioGroup) view.findViewById(R.id.rg_community_header);

        vp_community_content = (ViewPager) view.findViewById(R.id.vp_community_content);

        vp_community_content.setOffscreenPageLimit(3);

        //对头部进行监听
        for (int i = 0; i < 3; i++) {
            RadioButton rb= (RadioButton) rg_community_header.getChildAt(i);
            rb.setOnClickListener(this);
            rb.setId(i);
        }

        communityPagerAdapter = new CommunityPagerAdapter(getActivity().getSupportFragmentManager());

        vp_community_content.setAdapter(communityPagerAdapter);

        view.findViewById(R.id.iv_fragment_community_note).setOnClickListener(this);
        vp_community_content.setOnPageChangeListener(this);
    }

    //viewpager滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < 3; i++) {
                RadioButton childAt = (RadioButton) rg_community_header.getChildAt(i);
            if (position == i){
                childAt.setTextColor(getResources().getColor(R.color.colorYellow));
            }else{
                childAt.setTextColor(getResources().getColor(R.color.colorgray));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //我的监听事件
    @Override
    public void onClick(View v) {
        vp_community_content.setCurrentItem(v.getId());

        if (v.getId() == R.id.iv_fragment_community_note){
            Intent intent = new Intent(getActivity(), Home_Fragemnt_Note.class) ;
            startActivity(intent);
        }
    }


    class CommunityPagerAdapter extends FragmentPagerAdapter{

        public CommunityPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0){
                fragment = new SubCommunityFragment_Category();
            }
            else if (position == 1){
                fragment = SubCommunityFragment_Some.setFragment(0);
            }else if(position == 2){
                fragment = SubCommunityFragment_Some.setFragment(1);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
