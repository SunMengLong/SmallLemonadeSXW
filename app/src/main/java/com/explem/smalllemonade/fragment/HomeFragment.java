package com.explem.smalllemonade.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.Home_Fragment_Code_I_Know;
import com.explem.smalllemonade.Home_Fragment_Love_Shequ;
import com.explem.smalllemonade.Home_Fragment_Love_oxygen_nine;
import com.explem.smalllemonade.Lenmmon_Girl_Gift;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.adapter.LoveRecycleAdapter;
import com.explem.smalllemonade.adapter.Love_oxyGenAdapter;
import com.explem.smalllemonade.adapter.Love_oxyGenAdapter2;
import com.explem.smalllemonade.base.BaseFragment;
import com.explem.smalllemonade.bean.Home_Fragment_AirBean;
import com.explem.smalllemonade.bean.Home_Fragment_GiftBean;
import com.explem.smalllemonade.bean.Home_Fragment_Love_oxygen;
import com.explem.smalllemonade.bean.Home_Fragment_LunBo_Bean;
import com.explem.smalllemonade.bean.LoveCommunityBean;
import com.explem.smalllemonade.sql.Dao;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.Home_Fragemnt_Note;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;

/**
 * 主页Fragment
 *
 * @auto XuJiaJian
 * Created by Pooh on 2016/12/27.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "HomeFragment";
    //请求网络----轮播图
    int tag_lunbo = 0;
    int tag_gift = 1;
    int tag_love = 2;
    int tag_air = 3;
    int tag_oxygen=4;
    private View v;
    private TextView home_fragment_period;
    private ImageView home_fragment_note;
    private boolean flag = false;
    private boolean flag2 = false;
    private CheckBox home_fragment_pop_che_two;
    private CheckBox home_fragment_pop_che_one;
    private Home_Fragment_LunBo_Bean home_fragment_lunBo_bean;
    private Home_Fragment_GiftBean home_fragment_giftBean;
    int a = 0;
    private SharedPreferences.Editor edit;
    private ArrayList<String> pathList = new ArrayList<>();
    private  ArrayList<Home_Fragment_Love_oxygen  .DataBean> oxygnlist=new ArrayList<>();
    private  ArrayList<Home_Fragment_Love_oxygen  .DataBean> oxygnlist2=new ArrayList<>();

    //  轮播图接口
    public static String path_lunbo = "http://www.yulin520.com/a2a/broadcast/files";
    public static String args_lunbo = "sign=7442C54B6DAFB81CEB01588164F3CCA8&ts=1482907765&pageSize=9&page=1";
    //礼物推荐
    public static String path_gift = "http://www.yulin520.com/a2a/festival/next";
    public static String args_gift = "sign=6456E4A00F1FBAFBBD5B5AF2BD01126A&ts=1482905506";
    //恋乎社区
    public static String path_love = "http://www.yulin520.com/a2a/forum/recommend/withIndexImg";
    public static String args_lovw = "sign=D6784045D12FF858A54332CF3CDC4A6C&pageSize=12&emotionStage=2&ts=1482905506&page=1";
    //冷暖共知
    public static String path_air = "http://www.yulin520.com/a2a/news/dissertation";
    public static String args_air = "sign=7D4ED43C186CA4EDEB99193024F9BEF2&pageSize=1&queryData=&ts=1482905506&page=1";
    //恋爱氧气
    public static String path_oxygen="http://www.yulin520.com/a2a/news/sd/list";
    public  static  String args_oxygen="sign=FF249FC05B920D994BE888EBD6F68133&ts=1482905506&pageSize=6&page=1";
    public static Dao dao;
    public static String data;
    private ViewPager home_fragment_viewPager;
    private LinearLayout home_fragment_lin;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == tag_lunbo) {
                Home_Fragment_LunBo_Bean home_fragment_lunBo_bean = (Home_Fragment_LunBo_Bean) msg.obj;
                //拿圆点
                getDoc(home_fragment_lunBo_bean);
                //轮播图的数据适配器
                setMyAdapter(home_fragment_lunBo_bean);
            }
            if (msg.arg1 == tag_gift) {
                home_fragment_giftBean = (Home_Fragment_GiftBean) msg.obj;
                //   if (home_fragment_giftBean.getData() != null) {
                //     Glide.with(getActivity()).load(R.mipmap.wansheng).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(home_fragment_snow_people);
                home_fragment_snow_people.setImageResource(R.mipmap.wansheng);
                home_fragment_yuandan.setImageResource(R.mipmap.qixi);
            }
            //恋乎社区
            if (msg.arg1 == tag_love) {
                loveCommunityBean = (LoveCommunityBean) msg.obj;
                love_viewPager.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return 4;
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object object) {
                        return view == object;
                    }

                    @Override
                    public Object instantiateItem(ViewGroup container, int position) {
                        list_item = CuChun(position);
                        View view = View.inflate(getActivity(), R.layout.home_fragment_love_, null);
                        RecyclerView home_fragment__love_recycle = (RecyclerView) view.findViewById(R.id.home_fragment__love_recycle);
                        //设置Recycle布局管理器
                        home_fragment__love_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                        LoveRecycleAdapter loveRecycleAdapter = new LoveRecycleAdapter(getActivity(), list_item);
                        home_fragment__love_recycle.setAdapter(loveRecycleAdapter);
                        loveRecycleAdapter.setOnItemClickLitener(new LoveRecycleAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent in = new Intent(getActivity(), Home_Fragment_Love_Shequ.class);
                                //内容
                                in.putExtra("content", loveCommunityBean.getData().get(position).getContent());
                                //标题
                                in.putExtra("title", loveCommunityBean.getData().get(position).getTitle());
                                //楼主头像
                                in.putExtra("headImage", loveCommunityBean.getData().get(position).getHeadImg());
                                //楼主网名
                                in.putExtra("userName", loveCommunityBean.getData().get(position).getUserName());
                                //发送的时间
                                in.putExtra("createTime", loveCommunityBean.getData().get(position).getCreateTime());

                                startActivity(in);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });
                        container.addView(view);
                        return view;
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object) {
                        container.removeView((View) object);
                    }
                });
            }
            //冷暖共知
            if (msg.arg1 == tag_air) {
                home_fragment_airBean = (Home_Fragment_AirBean) msg.obj;
                Glide.with(getActivity()).load(home_fragment_airBean.getData().get(0).getImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(home_fragment_love_oxygen_img2);
            }
            //恋爱氧气
            if (msg.arg1 == tag_oxygen) {
                home_fragment_love_oxygen = (Home_Fragment_Love_oxygen) msg.obj;

                for (int i = 0; i < 3; i++) {
                    oxygnlist.add(home_fragment_love_oxygen.getData().get(i));
                    String substring = oxygnlist.get(0).getStartTime().substring(5, 10);
                    love_air_tv.setText(substring);
                    Love_oxyGenAdapter love_oxyGenAdapter = new Love_oxyGenAdapter(getActivity(), oxygnlist);
                    home_fragment_love_oxygen_recyle.setAdapter(love_oxyGenAdapter);
                    love_oxyGenAdapter.setOnItemClickLitener(new Love_oxyGenAdapter.OnItemClickLitener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent in = new Intent(getActivity(), Home_Fragment_Love_oxygen_nine.class);
                            in.putExtra("url", oxygnlist.get(position).getUrl());
                            startActivity(in);
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {

                        }
                    });
                }
                oxygnlist2.clear();
                for (int i = 4; i < 6; i++) {
                    oxygnlist2.add(home_fragment_love_oxygen.getData().get(i));
                    String substring = oxygnlist2.get(0).getStartTime().substring(5, 10);
                    love_air_tv2.setText(substring);
                    Love_oxyGenAdapter2 love_oxyGenAdapter2 = new Love_oxyGenAdapter2(getActivity(), oxygnlist2);
                    home_fragment_love_oxygen_recyle2.setAdapter(love_oxyGenAdapter2);
                    love_oxyGenAdapter2.setOnItemClickLitener(new Love_oxyGenAdapter2.OnItemClickLitener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent in = new Intent(getActivity(), Home_Fragment_Love_oxygen_nine.class);
                            in.putExtra("url2", oxygnlist2.get(position).getUrl());
                            startActivity(in);
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {

                        }
                    });
                }


            }
        }
    };
    private LinearLayout home_fragment__love_lin;
    private ArrayList<LoveCommunityBean.DataBean> list_item;
    private ImageView home_fragment_love_oxygen_img2;
    private Home_Fragment_AirBean home_fragment_airBean;
    private TextView love_air_tv, love_air_tv2;
    private Home_Fragment_Love_oxygen home_fragment_love_oxygen;
    private RelativeLayout home_fragment_rel;
    private RecyclerView home_fragment_love_oxygen_recyle, home_fragment_love_oxygen_recyle2;

    @NonNull
    private ArrayList<LoveCommunityBean.DataBean> CuChun(int position) {
        //存放单个条目的数据
        ArrayList<LoveCommunityBean.DataBean> list_item = new ArrayList<>();
        //晴空集合
        if (list_item != null) {
            list_item.clear();
        }
        //判断下表
        if (position == 0) {
            for (int i = 0; i < 3; i++) {
                list_item.add(loveCommunityBean.getData().get(i));
            }
        } else if (position == 1) {
            for (int i = 3; i < 6; i++) {
                list_item.add(loveCommunityBean.getData().get(i));
            }
        } else if (position == 2) {
            for (int i = 6; i < 9; i++) {
                list_item.add(loveCommunityBean.getData().get(i));
            }
        } else if (position == 3) {
            for (int i = 9; i < 12; i++) {
                list_item.add(loveCommunityBean.getData().get(i));
            }
        }
        return list_item;
    }

    private ImageView home_fragment_snow_people;
    private ImageView home_fragment_yuandan;
    private Button home_fragment__gift;
    private String data2;
    private ViewPager love_viewPager;
    private LoveCommunityBean loveCommunityBean;


    @Override
    protected void onload() {
        //设置布局
        HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {

        v = View.inflate(getActivity(), R.layout.home_fragment, null);
        initView();
        return v;
    }

    private void initView() {
        //恋爱期
        home_fragment_period = (TextView) v.findViewById(R.id.home_fragment_period);
//        //短信
        home_fragment_note = (ImageView) v.findViewById(R.id.home_fragment_note);
//        //轮播图
        home_fragment_viewPager = (ViewPager) v.findViewById(R.id.home_fragment_viewPager);
        //轮播图圆点
        home_fragment_lin = (LinearLayout) v.findViewById(R.id.home_fragment_lin);
        //雪人图片
        home_fragment_snow_people = (ImageView) v.findViewById(R.id.home_fragment_snow_people);
        //元旦
        home_fragment_yuandan = (ImageView) v.findViewById(R.id.home_fragment_yuandan);
        //礼物推荐
        home_fragment__gift = (Button) v.findViewById(R.id.home_fragment__gift);
        //恋乎社区的轮播图
        love_viewPager = (ViewPager) v.findViewById(R.id.love_viewPager);
        //恋爱社区的圆点
        home_fragment__love_lin = (LinearLayout) v.findViewById(R.id.home_fragment__love_lin);
        //冷暖共知
        home_fragment_love_oxygen_img2 = (ImageView) v.findViewById(R.id.home_fragment_love_oxygen_img2);
        //恋爱氧气
        love_air_tv = (TextView) v.findViewById(R.id.love_air_tv);
        love_air_tv2 = (TextView) v.findViewById(R.id.love_air_tv2);
        //恋爱氧气12-19
        home_fragment_love_oxygen_recyle = (RecyclerView) v.findViewById(R.id.home_fragment_love_oxygen_recyle);
        home_fragment_love_oxygen_recyle2 = (RecyclerView) v.findViewById(R.id.home_fragment_love_oxygen_recyle3);

        getLoveDoc();
        //请求网络
        getData(path_lunbo, args_lunbo, tag_lunbo);
        //设置轮播图动画
        getLunBoAnim();
        //请求礼物推荐
        getData(path_gift, args_gift, tag_gift);
        // 请求恋乎社区
        getData(path_love, args_lovw, tag_love);
        //冷暖共知
        getData(path_air, args_air, tag_air);
        //恋爱氧气
        getData(path_oxygen, args_oxygen, tag_oxygen);
        //平铺
        home_fragment_love_oxygen_img2.setScaleType(ImageView.ScaleType.FIT_XY);
        home_fragment_love_oxygen_img2.setOnClickListener(this);
        //恋爱氧气的适配器
        home_fragment_love_oxygen_recyle.setLayoutManager(new LinearLayoutManager(getActivity()));
        home_fragment_love_oxygen_recyle2.setLayoutManager(new LinearLayoutManager(getActivity()));

        //设置恋爱社区轮播图的原点
        love_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 4; i++) {
                    ImageView image = (ImageView) home_fragment__love_lin.getChildAt(i);
                    if (position == i) {
                        image.setImageResource(R.drawable.dot_focuse);
                    } else {
                        image.setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        home_fragment__gift.setOnClickListener(this);
        home_fragment_period.setOnClickListener(this);
        home_fragment_note.setOnClickListener(this);
        home_fragment_yuandan.setOnClickListener(this);
        home_fragment_snow_people.setOnClickListener(this);
    }

    //拿恋爱社区的圆点
    ArrayList<ImageView> loveList = new ArrayList<>();

    private void getLoveDoc() {
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(getActivity());
            if (i == 0) {
                imageView.setImageResource(R.drawable.dot_focuse);
            } else {
                imageView.setImageResource(R.drawable.dot_normal);
            }
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 0);
            home_fragment__love_lin.addView(imageView, params);
            loveList.add(imageView);
        }
    }

    //添加圆点
    ArrayList<ImageView> docList = new ArrayList<>();

    private void getDoc(Home_Fragment_LunBo_Bean home_fragment_lunBo_bean) {
        docList.clear();
        Log.i(TAG, "getDoc: ***" + home_fragment_lunBo_bean.getData().size());
        if (home_fragment_lin.getChildCount() <= 0) {
            for (int i = 0; i < home_fragment_lunBo_bean.getData().size(); i++) {
                ImageView imageView = new ImageView(getActivity());
                if (i == 0) {
                    // 如果是第一张，默认给一个亮的小点
                    imageView.setImageResource(R.drawable.dot_focuse);
                } else {
                    // 如果不是滴一个，默认给一个暗的小点
                    imageView.setImageResource(R.drawable.dot_normal);
                }
                // 设置小点的默认宽高为20dp
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
                // 设置小点的间距
                params.setMargins(5, 0, 5, 0);
                home_fragment_lin.addView(imageView, params);
                // 往小点集合中添加view
                docList.add(imageView);
            }
        }

    }

    //设置轮播图动画
    private void getLunBoAnim() {
        home_fragment_viewPager.setOffscreenPageLimit(3);
        home_fragment_viewPager.setPageMargin(50);
        home_fragment_viewPager.setCurrentItem(3000);
        //给轮播图设置动画
        home_fragment_viewPager.setPageTransformer(true,
                new AlphaPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));
        //添加圆点
        home_fragment_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 遍历小点的集合
                for (int i = 0; i < docList.size(); i++) {
                    // 如果当前的索引值和i相等
                    if (position % docList.size() == i) {
                        // 设置小点为亮色
                        docList.get(i).setImageResource(R.drawable.dot_focuse);
                    } else {
                        // 否则暗色
                        docList.get(i).setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.home_fragment_period:
                //选择时期
                Peroid();
                break;
                //短信图片
            case R.id.home_fragment_note:
                enterIntent(Home_Fragemnt_Note.class);
                break;
             //雪人图片
            case R.id.home_fragment_snow_people:
                PopUp();
            break;
            //元旦节
            case R.id.home_fragment_yuandan:
                PopUp();
                break;
            //推荐礼物
            case R.id.home_fragment__gift:
                Toast.makeText(getActivity(), "礼物推荐", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Lenmmon_Girl_Gift.class);

                startActivity(intent);

                break;
            case R.id.home_fragment_love_oxygen_img2:
                Intent in2 = new Intent(getActivity(), Home_Fragment_Code_I_Know.class);
                //   if(home_fragment_airBean.getData()!=null){
                in2.putExtra("url4", home_fragment_airBean.getData().get(0).getUrl());

                //  }
                startActivity(in2);
                break;

            default:
                break;
        }

    }
    //助攻节目PopWindow
    private void PopUp() {
        View vv = View.inflate(getActivity(), R.layout.home_fragment_zhugongjiemu, null);
//        WebView home_fragment_web_zhugong = (WebView) vv.findViewById(R.id.home_fragment_web_zhugong);
        //home_fragment_web_zhugong.loadUrl(home_fragment_giftBean.getData().getHolidayDetails());
        PopupWindow pop = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, 1100);
        int height = pop.getHeight();
        int width = pop.getWidth();
        //pop属性使用系统退出键 推出POP
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        //点击popWindow后背景变暗
        if (pop.isShowing()) {
            // 关闭
            pop.dismiss();
        } else {
            pop.showAtLocation(v, Gravity.LEFT | Gravity.TOP, v.getWidth() / 2 - width / 2, v.getHeight() / 2 - height / 2);
            backgroundAlpha(0.3f);
        }
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupwindow消失的时候恢复成原来的透明度
                backgroundAlpha(1f);
            }
        });
        if (pop.isShowing()) {
            // 关闭
            pop.dismiss();
        } else {
            pop.showAtLocation(v, Gravity.LEFT | Gravity.TOP, v.getWidth() / 2 - width / 2, v.getHeight() / 2 - height / 2);
            backgroundAlpha(0.3f);
        }
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupwindow消失的时候恢复成原来的透明度
                backgroundAlpha(1f);
            }
        });
    }

    //选择时期
    void Peroid() {
        View vv = View.inflate(getActivity(), R.layout.home_fragment_period_pop, null);
        //确定按钮
        Button home_fragment_pop_true = (Button) vv.findViewById(R.id.home_fragment_pop_true);
        //恋爱期
        home_fragment_pop_che_two = (CheckBox) vv.findViewById(R.id.home_fragment_pop_che_two);
        //单身期
        home_fragment_pop_che_one = (CheckBox) vv.findViewById(R.id.home_fragment_pop_che_one);
        final PopupWindow pop = new PopupWindow(vv, 800, 800);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.setAnimationStyle(R.style.Popupwindow);
        int height = pop.getHeight();
        int width = pop.getWidth();
        //点击popWindow后背景变暗
        if (pop.isShowing()) {
            // 关闭
            pop.dismiss();
        } else {
            pop.showAtLocation(v, Gravity.LEFT | Gravity.TOP, v.getWidth() / 2 - width / 2, v.getHeight() / 2 - height / 2);
            backgroundAlpha(0.3f);
        }
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupwindow消失的时候恢复成原来的透明度
                backgroundAlpha(1f);
            }
        });
        //确定按钮
        home_fragment_pop_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });
        if (a == 0) {
            home_fragment_pop_che_two.setChecked(true);
            Love();
            Single();
        }
    }

    //单身期
    void Single() {
        home_fragment_pop_che_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!flag2) {
                    home_fragment_pop_che_one.setChecked(true);
                    home_fragment_pop_che_two.setChecked(false);
                    home_fragment_period.setText("单身期");
                    flag2 = true;
                } else {
                    home_fragment_pop_che_one.setChecked(false);
                    home_fragment_pop_che_two.setChecked(true);
                    home_fragment_period.setText("恋爱期");
                    flag2 = false;
                }

            }
        });
    }

    //恋爱期
    void Love() {

        home_fragment_pop_che_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!flag) {
                    home_fragment_pop_che_two.setChecked(true);
                    home_fragment_pop_che_one.setChecked(false);
                    home_fragment_period.setText("恋爱期");
                    flag = true;
                } else {
                    home_fragment_pop_che_two.setChecked(false);
                    home_fragment_pop_che_one.setChecked(true);
                    home_fragment_period.setText("单身期");
                    flag = false;
                }

            }
        });
    }

    //枢纽 / 意图
    public void enterIntent(Class c) {
        Intent intent = new Intent(getActivity(), c);
        startActivity(intent);
    }

    //popupWindow背景
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }

    //请求数据的方法
    public void getData(String path, String args, final int tag) {
        new BaseDate(getActivity()) {

            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
            }

            @Override
            public void setResultData(String data) {
                //HomeFragment.this.data = data;
                Gson gson = new Gson();
                if (tag_lunbo == tag) {
                    home_fragment_lunBo_bean = gson.fromJson(data, Home_Fragment_LunBo_Bean.class);
                    //发送到Handler中
                    Message msg = new Message();
                    msg.obj = home_fragment_lunBo_bean;
                    msg.arg1 = tag;
                    handler.sendMessage(msg);
                }
                if (tag_gift == tag) {
                    home_fragment_giftBean = gson.fromJson(data, Home_Fragment_GiftBean.class);
                    Message msg2 = new Message();
                    msg2.obj = home_fragment_giftBean;
                    msg2.arg1 = tag;
                    handler.sendMessage(msg2);
                }
                if (tag_love == tag) {
                    loveCommunityBean = gson.fromJson(data, LoveCommunityBean.class);
                    Message msg3 = new Message();
                    msg3.obj = loveCommunityBean;
                    msg3.arg1 = tag;
                    handler.sendMessage(msg3);
                }
                if (tag_air == tag) {
                    //    Log.i("sss", "setResultData:**** "+data);
                    home_fragment_airBean = gson.fromJson(data, Home_Fragment_AirBean.class);
                    Message msg4 = new Message();
                    msg4.obj = home_fragment_airBean;
                    msg4.arg1 = tag;
                    handler.sendMessage(msg4);
                }
                if (tag_oxygen == tag) {
                    home_fragment_love_oxygen = gson.fromJson(data, Home_Fragment_Love_oxygen.class);
                    Message msg5 = new Message();
                    msg5.obj = home_fragment_love_oxygen;
                    msg5.arg1 = tag;
                    handler.sendMessage(msg5);
                }

            }
        }.getDate(path, args, 1, BaseDate.NOTIME);
    }


    //轮播图的适配器
    public void setMyAdapter(final Home_Fragment_LunBo_Bean home_fragment_lunBo_bean) {
        home_fragment_viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView img = new ImageView(getActivity());
                Glide.with(getActivity()).load(home_fragment_lunBo_bean.getData().get(position % home_fragment_lunBo_bean.getData().size()).getImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(img);
                container.addView(img);
                //ViewPager 点击
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < home_fragment_lunBo_bean.getData().size(); i++) {
                            Toast.makeText(getActivity(), "图片" + i, Toast.LENGTH_LONG).show();
                        }
                    }
                });
                return img;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }
}
