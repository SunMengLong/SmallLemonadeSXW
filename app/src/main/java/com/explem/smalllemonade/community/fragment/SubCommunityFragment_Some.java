package com.explem.smalllemonade.community.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.base.BaseFragment;
import com.explem.smalllemonade.community.adapter.SubCommunityFragmentAdapter;
import com.explem.smalllemonade.community.bean.CommunityContent;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.utils.CommonUtils;
import com.explem.smalllemonade.view.ShowingPage;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class SubCommunityFragment_Some extends BaseFragment implements SpringView.OnFreshListener, AdapterView.OnItemClickListener{

    public int page = 1;


    public String pathSelected = "http://www.yulin520.com/a2a/forum/allTypeList";
    public String argsSelected = "sign=0A1CA7FA70FD4F4B1E141438594A4C10&pageSize=10&sort=2&ts=1482920553&page=1&forumType=";

    public String pathAll = "http://www.yulin520.com/a2a/forum/allTypeList";
    public String argsAll = "sign=1ED39AA49B6594114FB04896D4716775&pageSize=10&sort=2&ts=1482920354&page=1&forumType=";
    public List<CommunityContent.Data> dataList;
    public View view;
    public int type=0;
    public MyBaseData myBaseData;
    public SpringView springview_subcommunityfragment;
    public SubCommunityFragmentAdapter adapter;
    List<CommunityContent.Data> finalList = new ArrayList<CommunityContent.Data>();
    public ListView listview_subcommunityfragment;
    public ImageView iv_floating_post;

    public boolean isGoneAnimation = false;
    public boolean isVisbleAnimation = false;

    @Override
    protected void onload() {

//        Bundle bundle = getArguments();
//        type = bundle.getInt("type");
        myBaseData = new MyBaseData(getActivity());

        getWebData(page);

    }

    public void getWebData(int page) {
        if (type == 0) {
            myBaseData.getDate(pathSelected, argsSelected + page, 0, 0);
        } else {
            myBaseData.getDate(pathAll, argsAll + page, 0, 0);
        }
    }

    @Override
    protected View createSuccessView() {
        view = CommonUtils.inflate(R.layout.subcommunityfragment_all);
        listview_subcommunityfragment = (ListView) view.findViewById(R.id.stview_subcommunityfragment);

        iv_floating_post = (ImageView) view.findViewById(R.id.iv_floating_post);

        if (type == 0) {
            iv_floating_post.setVisibility(View.GONE);
        } else {
            iv_floating_post.setVisibility(View.VISIBLE);
        }

        springview_subcommunityfragment = (SpringView) view.findViewById(R.id.springview_subcommunityfragment);
        springview_subcommunityfragment.setHeader(new DefaultHeader(getActivity()));
        springview_subcommunityfragment.setFooter(new DefaultHeader(getActivity()));
        springview_subcommunityfragment.setListener(this);
        springview_subcommunityfragment.setType(SpringView.Type.FOLLOW);

        listview_subcommunityfragment.setOnItemClickListener(this);
        listview_subcommunityfragment.setOnTouchListener(new View.OnTouchListener() {

            private float downY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = motionEvent.getY();
                        //手指向下滑动，让Button显示（执行显示的动画）
                        Log.i("AAAA----", downY + "------" + moveY);
                        if (moveY - downY > 70 &&!isVisbleAnimation&&iv_floating_post.getVisibility()!=View.VISIBLE) {
                            visibleAnimation();
                            isVisbleAnimation=true;
                            moveY = downY;
                            //手指向上滑动，让Button隐藏(执行隐藏的动画)
                        } else if (downY - moveY > 70 && !isGoneAnimation) {
                            moveY = downY;
                            isGoneAnimation = true;
                            goneAnimation();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isGoneAnimation = false;
                        isVisbleAnimation=false;
                        break;
                }
                return false;
            }
        });

        initAdapter();

        return view;
    }

    public void visibleAnimation() {
        iv_floating_post.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2, Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(600);
        translateAnimation.setFillBefore(true);
        translateAnimation.setFillAfter(true);
        iv_floating_post.startAnimation(translateAnimation);
    }

    public void goneAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2);
        translateAnimation.setDuration(800);
        translateAnimation.setFillBefore(true);
        translateAnimation.setFillAfter(true);
        iv_floating_post.startAnimation(translateAnimation);
        iv_floating_post.setVisibility(View.GONE);
    }

    private void initAdapter() {

        finalList.addAll(dataList);
        if (adapter == null || page == 1) {
            adapter = new SubCommunityFragmentAdapter(getActivity(), finalList);
            listview_subcommunityfragment.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    //上拉加载 下拉刷新
    @Override
    public void onRefresh() {
        finalList.clear();
        page = 1;
        getWebData(page);
        initAdapter();
        stop();
    }

    public void stop() {
        springview_subcommunityfragment.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {
        ++page;
        getWebData(page);
        initAdapter();
        stop();
    }

    //list条目监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "this is item NO." + position, Toast.LENGTH_SHORT).show();
    }

    //请求数据
    public class MyBaseData extends BaseDate {

        public MyBaseData(Context context) {
            super(context);
        }

        @Override
        public void setResultError(ShowingPage.StateType stateLoadError) {

        }

        @Override
        public void setResultData(String data) {
            Gson gson = new Gson();
            CommunityContent communityContent = gson.fromJson(data, CommunityContent.class);
            dataList = communityContent.getData();

            SubCommunityFragment_Some.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }
    }

    public static Fragment setFragment(int type) {
        SubCommunityFragment_Some subCommunityFragment = new SubCommunityFragment_Some();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        subCommunityFragment.setArguments(bundle);
        return subCommunityFragment;
    }
}
