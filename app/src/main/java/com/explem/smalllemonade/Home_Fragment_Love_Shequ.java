package com.explem.smalllemonade;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.adapter.MyListViewAdapter;
import com.explem.smalllemonade.bean.Love_shequ;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Fragment_Love_Shequ extends AppCompatActivity implements View.OnClickListener {
    private  int love_tag=0;
    private CheckBox home_fragment_love_shequ_che;
    private boolean flag = false;
    private CircleImageView home_fragment_love_shequ_touxiang;
    private Button home_fragment_love_shequ_queding;
    private Button home_fragment_love_shequ_quxiao;
    private PopupWindow popupWindow1;
    private ImageView home_fragment_love_shequ_dianzan_che;
    private TextView home_fragment_love_shequ_dianzan;
    private int a = 10;

    private ListView home_fragment_love_shequ_lv;
    private ArrayList<Integer> Imagelist;
    private ImageView home_fragment_love_shequ_heart, home_fragment_love_shequ_heart2,home_fragment_love_shequ_heart3;
    public  static String path_love="http://www.yulin520.com/a2a/forumReply/detailedShow";
    public  static String args_love="pageSize=10&sign=6F34FCC12B19E91E8514949EAFC911AA&sort=1&ts=1609018744&page=1&id=";
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==love_tag){
                love_shequ = (Love_shequ) msg.obj;
                home_fragment_love_shequ_lv.setAdapter(new MyListViewAdapter(Home_Fragment_Love_Shequ.this,love_shequ.getData() ));
            }
        }
    };
    private Love_shequ love_shequ;

    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__fragment__love__shequ);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String headImage = intent.getStringExtra("headImage");
        String userName = intent.getStringExtra("userName");
        String createTime = intent.getStringExtra("createTime");
        String content = intent.getStringExtra("content");
        String title = intent.getStringExtra("title");
        id = intent.getIntExtra("id",-1);
        final ImageView fanhui = (ImageView) findViewById(R.id.fanhui);
        home_fragment_love_shequ_che = (CheckBox) findViewById(R.id.home_fragment_love_shequ_che);
        //头像
        home_fragment_love_shequ_touxiang = (CircleImageView) findViewById(R.id.home_fragment_love_shequ_touxiang);
        //网名
        TextView home_fragment_love_shequ_name = (TextView) findViewById(R.id.home_fragment_love_shequ_name);
        //时间
        //TextView home_fragment_love_shequ_time = (TextView) findViewById(R.id.home_fragment_love_shequ_time);
        //标题
        TextView home_fragment_love_shequ_title = (TextView) findViewById(R.id.home_fragment_love_shequ_title);
        //正文
        TextView home_fragment_love_shequ_content = (TextView) findViewById(R.id.home_fragment_love_shequ_content);
        //点赞
        home_fragment_love_shequ_dianzan_che = (ImageView) findViewById(R.id.home_fragment_love_shequ_dianzan_che);
        //点赞数量
        home_fragment_love_shequ_dianzan = (TextView) findViewById(R.id.home_fragment_love_shequ_dianzan);
        //心形图片
        home_fragment_love_shequ_heart = (ImageView) findViewById(R.id.home_fragment_love_shequ_heart);
        home_fragment_love_shequ_heart2 = (ImageView) findViewById(R.id.home_fragment_love_shequ_heart2);
        home_fragment_love_shequ_heart3 = (ImageView) findViewById(R.id.home_fragment_love_shequ_heart3);
        //ListView  帖子
        home_fragment_love_shequ_lv = (ListView) findViewById(R.id.home_fragment_love_shequ_lv);
        home_fragment_love_shequ_dianzan.setText(a + "");
        home_fragment_love_shequ_name.setText(userName);
        home_fragment_love_shequ_title.setText(title);
        home_fragment_love_shequ_content.setText(content);
        Glide.with(Home_Fragment_Love_Shequ.this).load(headImage).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(home_fragment_love_shequ_touxiang);
        //请求网络
        getNet(path_love,args_love,love_tag);
        home_fragment_love_shequ_che.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private LinearLayout home_fragment_pop_jubao;
            private PopupWindow popupWindow;
            private TextView home_fragment_pop_tv;
            private CheckBox home_fragment_pop_che;

                //pop举报
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                View v = View.inflate(Home_Fragment_Love_Shequ.this, R.layout.home_fragment_tiezi_xiangqing_pop, null);
                LinearLayout home_fragment_pop_lin = (LinearLayout) v.findViewById(R.id.home_fragment_pop_lin);
                home_fragment_pop_che = (CheckBox) v.findViewById(R.id.home_fragment_pop_che);
                home_fragment_pop_tv = (TextView) v.findViewById(R.id.home_fragment_pop_tv);
                home_fragment_pop_jubao = (LinearLayout) v.findViewById(R.id.home_fragment_pop_jubao);
                LinearLayout home_fragment_pop_louzhu = (LinearLayout) v.findViewById(R.id.home_fragment_pop_louzhu);
                popupWindow = new PopupWindow(v, 400, 450);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(home_fragment_love_shequ_che, 10, 10);
                //取消
                home_fragment_pop_lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PanDuan();

                    }
                    private void PanDuan() {
                        if (!flag) {
                            home_fragment_pop_tv.setText("取消");
                            popupWindow.dismiss();
                        } else {
                            home_fragment_pop_tv.setText("倒叙排序");
                            popupWindow.dismiss();

                        }
                    }
                });
                //看楼主
                home_fragment_pop_louzhu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Home_Fragment_Love_Shequ.this, "该楼主没跟帖哦··", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                //举报
                home_fragment_pop_jubao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        popupWindow.dismiss();
                        View vv = View.inflate(Home_Fragment_Love_Shequ.this, R.layout.home_fragment_tishi_pop, null);
                        home_fragment_love_shequ_queding = (Button) vv.findViewById(R.id.home_fragment_love_shequ_queding2);
                        home_fragment_love_shequ_quxiao = (Button) vv.findViewById(R.id.home_fragment_love_shequ_quxiao2);
                        popupWindow1 = new PopupWindow(vv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupWindow1.setOutsideTouchable(true);
                        popupWindow1.setBackgroundDrawable(new BitmapDrawable());

                        popupWindow1.setAnimationStyle(R.style.popwin_anim_style);
                        home_fragment_love_shequ_queding.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(Home_Fragment_Love_Shequ.this, "举报成功！我们工作人员1--3个工作日内对此进行审核，多谢合作", Toast.LENGTH_SHORT).show();
                                popupWindow1.dismiss();
                            }
                        });
                        home_fragment_love_shequ_quxiao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow1.dismiss();
                            }
                        });
                        if (popupWindow1.isShowing()) {
                            // 关闭
                            popupWindow1.dismiss();
                        } else {
                            popupWindow1.showAtLocation(vv, Gravity.CENTER | Gravity.CENTER, 0, 0);
                            backgroundAlpha(0.3f);
                        }
                        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                //popupwindow消失的时候恢复成原来的透明度
                                backgroundAlpha(1f);
                            }
                        });
                    }
                });
            }
        });
        //点赞图片
        home_fragment_love_shequ_dianzan_che.setOnClickListener(new View.OnClickListener() {

            private int g;
            private int d;

            @Override
            public void onClick(View view) {
                if (!flag) {
                    home_fragment_love_shequ_dianzan_che.setImageResource(R.mipmap.community_detail_like_pressed);
                    a++;
                    //拿心形图片
                    getImage();
                    for (int i = 0; i < 9; i++) {
                        int v = (int) ((Math.random() * 10) - 1);
                        home_fragment_love_shequ_heart.setImageResource(Imagelist.get(v));
                        home_fragment_love_shequ_heart2.setImageResource(Imagelist.get((v+20)%Imagelist.size()));
                        home_fragment_love_shequ_heart3.setImageResource(Imagelist.get((v+10)%Imagelist.size()));
                        home_fragment_love_shequ_heart.setVisibility(View.VISIBLE);
                        home_fragment_love_shequ_heart2.setVisibility(View.VISIBLE);
                        home_fragment_love_shequ_heart3.setVisibility(View.VISIBLE);
                    }
                    home_fragment_love_shequ_dianzan.setText(a + "");
                    int b = (int) (Math.random() * (20) + 1);
                    int c = (int) (Math.random() * (20) + 1);
                    int e = (int) (Math.random() * (-20) - 1);
                    int f = (int) (Math.random() * (-20) - 1);
                    d = b + c;
                    g = e+f;
                    GetAnimation();
                    flag = true;
                } else {
                    home_fragment_love_shequ_dianzan_che.setImageResource(R.mipmap.community_detail_like);
                    a--;
                    home_fragment_love_shequ_dianzan.setText(a + "");
                    flag = false;
                }
            }
            //拿动画
            private void GetAnimation() {
                //使心开会跑
                final TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, d, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -20f);
                final TranslateAnimation translateAnimation2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,g, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -20f);
                final TranslateAnimation translateAnimation3 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,0, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -20f);
                translateAnimation.setDuration(5000);
                translateAnimation2.setDuration(3000);
                translateAnimation3.setDuration(4000);
                home_fragment_love_shequ_heart.startAnimation(translateAnimation);
                home_fragment_love_shequ_heart2.startAnimation(translateAnimation2);
                home_fragment_love_shequ_heart3.startAnimation(translateAnimation3);
                translateAnimation3.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        home_fragment_love_shequ_heart3.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        home_fragment_love_shequ_heart2.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        home_fragment_love_shequ_heart.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        fanhui.setOnClickListener(this);
    }

    private void getImage() {
        Imagelist = new ArrayList<Integer>();
        Imagelist.add(R.mipmap.heart_1);
        Imagelist.add(R.mipmap.heart_2);
        Imagelist.add(R.mipmap.heart_3);
        Imagelist.add(R.mipmap.heart_4);
        Imagelist.add(R.mipmap.heart_5);
        Imagelist.add(R.mipmap.heart_6);
        Imagelist.add(R.mipmap.heart_7);
        Imagelist.add(R.mipmap.heart_8);
        Imagelist.add(R.mipmap.heart_9);
    }

    //popupWindow背景
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = Home_Fragment_Love_Shequ.this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        Home_Fragment_Love_Shequ.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        Home_Fragment_Love_Shequ.this.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;


        }
    }
    //请求网络
    public  void getNet(String path,String args, final int index ) {
        new BaseDate(Home_Fragment_Love_Shequ.this) {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }
            @Override
            public void setResultData(String data) {
                Gson gson =new Gson();
                if(love_tag == index){
                    love_shequ = gson.fromJson(data, Love_shequ.class);
                    //发送到Handler中
                    Message msg = new Message();
                    msg.obj = love_shequ;
                    msg.arg1 = index;
                    handler.sendMessage(msg);
                }
            }
        }.getDate(path,args+id,index,BaseDate.NOTIME);


    }
}
