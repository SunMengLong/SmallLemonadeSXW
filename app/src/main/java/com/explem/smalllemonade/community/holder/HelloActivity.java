package com.explem.smalllemonade.community.holder;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.community.bean.CommunityContent;
import com.explem.smalllemonade.community.decoration.SpacesItemDecoration;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.utils.Pathes;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.drm.DrmStore.DrmObjectType.CONTENT;
import static android.view.Gravity.TOP;
import static com.umeng.socialize.utils.DeviceConfig.context;

public class HelloActivity extends AppCompatActivity {

    public RecyclerView hello_recyclerview;
    private List<CommunityContent.Data> communityTopList = new ArrayList<>();
    private List<CommunityContent.Data> communityContentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        hello_recyclerview = (RecyclerView) findViewById(R.id.hello_recyclerview);
        hello_recyclerview.setLayoutManager(new LinearLayoutManager(HelloActivity.this));
        hello_recyclerview.addItemDecoration(new SpacesItemDecoration(10));

        getWebData(Pathes.CommonTopPath, Pathes.FirstTopArgs, TOP);
        getWebData(Pathes.CommonContentPath, Pathes.FirstContentArgs, CONTENT);
    }

    public void getWebData(String path, String args, final int flag) {
        new BaseDate(HelloActivity.this) {
            @Override
            public void setResultError(ShowingPage.StateType stateLoadError) {
            }

            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                CommunityContent communityContent = gson.fromJson(data, CommunityContent.class);
                if (flag == TOP) {
                    Message msgTop = Message.obtain();
                    msgTop.obj = communityContent.getData();
                    msgTop.what = TOP;
                    handler.sendMessage(msgTop);
                } else if (flag == CONTENT) {
                    Message msgContent = Message.obtain();
                    msgContent.obj = communityContent.getData();
                    msgContent.what = CONTENT;
                    handler.sendMessage(msgContent);
                }
            }
        }.getDate(path, args, flag, 0);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<CommunityContent.Data> dataList = (List<CommunityContent.Data>) msg.obj;
            switch (msg.what) {
                case TOP:
                    if (dataList != null && dataList.size() != 0) {
                        communityTopList.addAll(dataList);
                    }
                    break;

                case CONTENT:
                    if (dataList != null && dataList.size() != 0) {
                        communityContentList.addAll(dataList);
                    }
                    break;
            }

            RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {

                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    RecyclerView.ViewHolder holder = null;
                    View view = null;
                    switch (viewType) {
                        case 0:  //position为0
                            view = View.inflate(HelloActivity.this, R.layout.item_community_top, null);
                            holder = new TopHolder(view);
                            break;
                        default:
                            view = LayoutInflater.from(HelloActivity.this).inflate(R.layout.subcommunityfragment_item,parent,false);
                            holder = new TopHolder(view);
                            break;
                    }
                    return holder;
                }

                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                    switch (position) {
                        case 0:
                            TopHolder topHolder = (TopHolder) holder;
                            topHolder.mylistview_top.setAdapter(new BaseAdapter() {
                                @Override
                                public int getCount() {
                                    return communityTopList.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return communityTopList.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    if (convertView == null) {
                                        convertView = View.inflate(HelloActivity.this, R.layout.category_fragment_item, null);
                                    }
                                    TextView tv_toptitle = (TextView) convertView.findViewById(R.id.tv_toptitle);
                                    tv_toptitle.setText(communityContentList.get(position).getTitle());
                                    return convertView;
                                }
                            });
                            break;

                        default:
                            ContentHolder contentHolder = (ContentHolder) holder;
                            contentHolder.tv_subcommunity_content.setText(communityContentList.get(position).getContent());
                            contentHolder.tv_subcommunity_title.setText(communityContentList.get(position).getTitle());
                            contentHolder.tv_subcommunity_name.setText(communityContentList.get(position).getUserName());
                            
                            break;
                    }
                }

                @Override
                public int getItemViewType(int position) {
                    int type = -1;
                    switch (position) {
                        case 0:
                            type = 0;
                            break;
                        default:
                            type = 1;
                            break;
                    }
                    return type;
                }

                @Override
                public int getItemCount() {
                    return communityContentList.size() + 1;
                }
            };
            //设置recyclerview的间距
            hello_recyclerview.setAdapter(adapter);

        }
    };
}
