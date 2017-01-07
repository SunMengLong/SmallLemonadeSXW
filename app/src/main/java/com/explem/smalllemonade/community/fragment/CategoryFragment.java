package com.explem.smalllemonade.community.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.base.BaseFragment;
import com.explem.smalllemonade.community.adapter.MyAdapter;
import com.explem.smalllemonade.community.adapter.MyRecyclerView;
import com.explem.smalllemonade.community.bean.CommunityContent;
import com.explem.smalllemonade.community.decoration.SpacesItemDecoration;
import com.explem.smalllemonade.fragment.HomeFragment;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.utils.CommonUtils;
import com.explem.smalllemonade.utils.Pathes;
import com.explem.smalllemonade.view.MyListView;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.content;
import static android.R.id.message;
import static com.explem.smalllemonade.R.id.tv_community_ditail_title;

/**
 * Created by Administrator on 2016/12/30 0030.
 */

public class CategoryFragment extends BaseFragment {
    //请求到的数据
//    public List<CommunityContent.Data> dataList;
    public View view;
    public List<CommunityContent> allList = new ArrayList<>();
    private static final int TOP = 100;
    private static final int CONTENT = 200;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CommunityContent communityContent = (CommunityContent) msg.obj;
            switch (msg.what) {
                case TOP:
                    MyAdapter myAdapter = new MyAdapter(getActivity(), communityContent.getData());
                    listview_category_fragment.setAdapter(myAdapter);
                    listview_category_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getActivity(),"item listview NO."+position,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

                case CONTENT:
                    recyclerview_category_fragment.setLayoutManager(new LinearLayoutManager(getActivity()));
                    MyRecyclerView recyclerViewAdapter = new MyRecyclerView(getActivity(), communityContent.getData());
                    //设置recyclerview的间距
                    recyclerview_category_fragment.addItemDecoration(new SpacesItemDecoration(10));
                    recyclerview_category_fragment.setAdapter(recyclerViewAdapter);
                    Log.i("hahaha","adapter的点击事件");
                    recyclerViewAdapter.setOnItemListener(new MyRecyclerView.OnItemListener() {
                        @Override
                        public void onItemClick(int position) {
                            Toast.makeText(getActivity(),"item recyclerview NO."+position,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }
    };
    public MyListView listview_category_fragment;
    public RecyclerView recyclerview_category_fragment;
    public String flag;

    @Override
    protected void onload() {
        Bundle bundle = getArguments();
        flag = bundle.getString("flag");
        Log.i("hahaha","argument收到的"+flag);
        CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {

        view = CommonUtils.inflate(R.layout.category_fragment_layout);

        listview_category_fragment = (MyListView) view.findViewById(R.id.listview_category_fragment);
        recyclerview_category_fragment = (RecyclerView) view.findViewById(R.id.recyclerview_category_fragment);

        getDataByFlag();

        return view;
    }

    public void getDataByFlag(){
        switch (flag){
            case "first":
                getWebData(Pathes.CommonTopPath, Pathes.FirstTopArgs, TOP);
                getWebData(Pathes.CommonContentPath, Pathes.FirstContentArgs, CONTENT);
                break;
            case "second":
                getWebData(Pathes.CommonTopPath, Pathes.SecondTopArgs, TOP);
                getWebData(Pathes.CommonContentPath, Pathes.SecondContentArgs, CONTENT);
                break;

            case "third":
                getWebData(Pathes.CommonTopPath, Pathes.ThirdTopArgs, TOP);
                getWebData(Pathes.CommonContentPath, Pathes.ThirdContentArgs, CONTENT);
                break;

            case "fourth":
                getWebData(Pathes.CommonTopPath, Pathes.FourthTopArgs, TOP);
                getWebData(Pathes.CommonContentPath, Pathes.FourthContentArgs, CONTENT);
                break;

            case "five":
                getWebData(Pathes.CommonTopPath, Pathes.FiveTopArgs, TOP);
                getWebData(Pathes.CommonContentPath, Pathes.FiveContentArgs, CONTENT);
                break;
        }
    }

    public static CategoryFragment getFragment(String flag){
        CategoryFragment categoryFragment = new CategoryFragment() ;
        Bundle bundle = new Bundle();
        bundle.putString("flag",flag);
        Log.i("hahaha","argument要传递的"+flag);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    public void getWebData(String path, String args, final int flag) {
        new BaseDate(getActivity()) {
            @Override
            public void setResultError(ShowingPage.StateType stateLoadError) {
            }

            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                CommunityContent communityContent = gson.fromJson(data, CommunityContent.class);
                if (flag == TOP) {
                    Message msgTop = Message.obtain();
                    msgTop.obj = communityContent;
                    msgTop.what = TOP;
                    handler.sendMessage(msgTop);
                } else if (flag == CONTENT) {
                    Message msgContent = Message.obtain();
                    msgContent.obj = communityContent;
                    msgContent.what = CONTENT;
                    handler.sendMessage(msgContent);
                }
            }
        }.getDate(path, args, flag, 0);
    }
}
