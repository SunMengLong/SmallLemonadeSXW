package com.explem.smalllemonade;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.adapter.MyFmLvAdapter;
import com.explem.smalllemonade.base.BaseActivity;
import com.explem.smalllemonade.bean.FmcommentBean;
import com.explem.smalllemonade.interces.OnMediaPlayerListener;
import com.explem.smalllemonade.services.MyService;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.MyListView;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.path;
import static android.R.attr.theme;

public class Home_Fragment_LunBoMusic extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, OnMediaPlayerListener {

    private String url;
    private String img;
    private String web;
    private WebView home_fragment_lunbo_music_web;
    private String title;
    private ImageButton imgbt_pause;
    private ImageView img_palyAanima;
    private SeekBar seekBar;
    private ImageView mv_imgBac;
    private TextView tv_curentp;
    private TextView tv_duration;
    private AnimationDrawable animationDrawable;
    private ImageButton imgbt_play;
    private ImageView img_pauseAnima;
    private String broadcastId;
    private MyService.MyBinder mybinder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mybinder = (MyService.MyBinder) iBinder;
            mybinder.setOnMediaPlayerListener(Home_Fragment_LunBoMusic.this);
            mybinder.helpStartPlay(url);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private MyListView music_myListView;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__fragment__lun_bo_music);
        gson = new Gson();
        getIntentData();
        initView();
        getDataFromNet();
        Glide.with(this).load(img).into(mv_imgBac);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
        seekBar.setOnSeekBarChangeListener(this);

    }

    //获取数据
    public void getIntentData() {
        Intent intent = getIntent();
        broadcastId = intent.getStringExtra("broadcastId");
        Log.i("TAG", "--------------------" + broadcastId);
        url = intent.getStringExtra("url");
        img = intent.getStringExtra("img");
        web = intent.getStringExtra("web");
        title = intent.getStringExtra("title");
    }

    private void initView() {
        img_palyAanima = (ImageView) findViewById(R.id.img_palyAanima);
        imgbt_pause = (ImageButton) findViewById(R.id.imgbt_pause);
        imgbt_play = (ImageButton) findViewById(R.id.imgbt_play);
        img_pauseAnima = (ImageView) findViewById(R.id.img_pauseAnima);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        mv_imgBac = (ImageView) findViewById(R.id.mv_imgBac);
        mv_imgBac.setScaleType(ImageView.ScaleType.FIT_XY);
        imgbt_pause.setOnClickListener(this);
        imgbt_play.setOnClickListener(this);
        img_pauseAnima.setOnClickListener(this);
        img_palyAanima.setOnClickListener(this);
        tv_curentp = (TextView) findViewById(R.id.tv_curentp);
        tv_duration = (TextView) findViewById(R.id.tv_duration);
        home_fragment_lunbo_music_web = (WebView) findViewById(R.id.home_fragment_lunbo_music_web);
        //WebView赋值
        home_fragment_lunbo_music_web.loadUrl(web);
        music_myListView = (MyListView) findViewById(R.id.music_myListView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
            conn = null;
        }
    }

    @Override
    public void onClick(View view) {
        mybinder.helpContinue();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            mybinder.sendCurentPosition(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void sendDurPosition(final int position) {
        seekBar.setMax(position);
        final SimpleDateFormat d = new SimpleDateFormat("mm:ss");//格式化时间
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_duration.setText(d.format(position));
            }
        });
    }

    @Override
    public void sendCrentPosition(final int position) {
        seekBar.setProgress(position);
        final SimpleDateFormat d = new SimpleDateFormat("mm:ss");//格式化时间
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_curentp.setText(d.format(position));
            }
        });
    }

    //正在播放
    @Override
    public void onPlay() {
        img_palyAanima.setVisibility(View.VISIBLE);
        imgbt_play.setVisibility(View.VISIBLE);
        img_pauseAnima.setVisibility(View.GONE);
        imgbt_pause.setVisibility(View.GONE);
        img_palyAanima.setImageResource(R.drawable.animation);
        animationDrawable = (AnimationDrawable) img_palyAanima.getDrawable();
        animationDrawable.start();
    }

    //暂停继续
    @Override
    public void onPauseing() {
        img_palyAanima.setVisibility(View.GONE);
        imgbt_play.setVisibility(View.GONE);
        img_pauseAnima.setVisibility(View.VISIBLE);
        imgbt_pause.setVisibility(View.VISIBLE);
        animationDrawable.stop();
    }

    //从网络中获取数据
    public void getDataFromNet() {
        BaseDate baseDate = new BaseDate(this) {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {

            }

            @Override
            public void setResultData(String data) {
                if (data != null) {
                    final FmcommentBean fmcommentBean = gson.fromJson(data, FmcommentBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<FmcommentBean.DataBean> dataList = fmcommentBean.getData();
                            Log.i("TAg", "-----------------" + dataList);
                            music_myListView.setAdapter(new MyFmLvAdapter(Home_Fragment_LunBoMusic.this, dataList));
                        }
                    });
                }
            }
        };
        baseDate.getDate("http://www.yulin520.com/a2a/broadcast/comment?page=1&pageSize=9&ts=-2129224150&broadcastId=" + broadcastId + "&sign=13EE6E92611F9B787A257B1B27924C5F", null, 0, 0);
    }
}