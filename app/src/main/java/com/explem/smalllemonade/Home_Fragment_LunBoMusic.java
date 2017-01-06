package com.explem.smalllemonade;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Fragment_LunBoMusic extends AppCompatActivity implements View.OnClickListener {

    private ImageView home_fragment_lunbo_music_img;
    private ImageView hom_lunbo_music_img_stop;
    private String url;
    private String img;
    private String web;
    private WebView home_fragment_lunbo_music_web;
    private MediaPlayer mediaPlayer;
    private AnimationDrawable animationDrawable;
    private ImageView hom_music_img_starts;
    private boolean flag = false;
    private ImageView hom_lunbo_music_img_close;
    private SeekBar music_img_seekbar;
    private Timer timer = new Timer();
    private int duration;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            seekPro = (Integer) msg.obj;
            music_img_seekbar.setProgress(seekPro);
            minute = (duration - seekPro) / 60000;
            seconds = (duration - seekPro) % 60000;
            //秒数
            second = Math.round((float) seconds / 1000);
            start_time.setText(minute + ":" + second + "");
            Log.i("uuuuuuuuuuuu", seekPro + "handleMessage: .........." + duration);
        }
    };
    private int seekPro;
    private TextView start_time;
    private long minute;
    private long seconds;
    private long second;
    private LinearLayout music_lin;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = new MediaPlayer();
        setContentView(R.layout.activity_home__fragment__lun_bo_music);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        img = intent.getStringExtra("img");
        web = intent.getStringExtra("web");
        title = intent.getStringExtra("title");
        initView();
    }


    private void initView() {
        //音乐图片
        home_fragment_lunbo_music_img = (ImageView) findViewById(R.id.home_fragment_lunbo_music_img);
        //停止图片
        hom_lunbo_music_img_stop = (ImageView) findViewById(R.id.hom_lunbo_music_img_stop);
        //开始动画图片
        hom_music_img_starts = (ImageView) findViewById(R.id.hom_music_img_starts);
        //WebView
        home_fragment_lunbo_music_web = (WebView) findViewById(R.id.home_fragment_lunbo_music_web);
        //关闭
        hom_lunbo_music_img_close = (ImageView) findViewById(R.id.hom_lunbo_music_img_close);
        music_lin = (LinearLayout) findViewById(R.id.music_lin);
        //进度条
        music_img_seekbar = (SeekBar) findViewById(R.id._music_img_seekbar);
        //开始时间
        start_time = (TextView) findViewById(R.id.start_time);
        //图片覆盖全局
        home_fragment_lunbo_music_img.setScaleType(ImageView.ScaleType.FIT_XY);
        //WebView赋值
        home_fragment_lunbo_music_web.loadUrl(web);
        View vv= View.inflate(Home_Fragment_LunBoMusic.this,R.layout.music_diantai,null);
        GridView music_gv = (GridView) vv.findViewById(R.id.music_gv);
        ArrayList<String> lists=new ArrayList<>();
         lists.add(img);
        lists.add(title);
     //   music_gv.setAdapter(new mYGriedAdapter(Home_Fragment_LunBoMusic.this,title,img));
        music_lin.addView(vv);
        //图片
        Glide.with(Home_Fragment_LunBoMusic.this).load(img).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(home_fragment_lunbo_music_img);
        music_img_seekbar.setMax(duration);
        home_fragment_lunbo_music_img.setOnClickListener(this);
        hom_lunbo_music_img_close.setOnClickListener(this);
        music_img_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
                //计算总长度为多少时间
                //分钟
                minute = (duration - i) / 60000;
                seconds = (duration - i) % 60000;
                //秒数
                second = Math.round((float) seconds / 1000);
                start_time.setText(minute + ":" + second + "");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //帧动画
        animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.musicframe1), 200);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.musicframe2), 200);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.musicframe3), 200);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.musicframe4), 200);
        animationDrawable.setOneShot(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_fragment_lunbo_music_img:
                play();
                //停止隐藏
                hom_lunbo_music_img_stop.setVisibility(View.INVISIBLE);
                //开始显示
                hom_music_img_starts.setVisibility(View.VISIBLE);
                hom_music_img_starts.setBackground(animationDrawable);
                hom_lunbo_music_img_close.setImageResource(R.mipmap.abc_stop);
                animationDrawable.start();
                break;
            case R.id.hom_lunbo_music_img_close:
                if (mediaPlayer.isPlaying() && mediaPlayer != null) {
                    mediaPlayer.pause();
                    hom_lunbo_music_img_stop.setVisibility(View.VISIBLE);
                    hom_music_img_starts.setVisibility(View.INVISIBLE);
                    hom_lunbo_music_img_close.setImageResource(R.mipmap.abc_ic_go_search_api_mtrl_alpha);
                }
                break;
        }
    }

    //播放音乐
    private void play() {
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(Home_Fragment_LunBoMusic.this, Uri.parse(url));
        mediaPlayer.start();
        duration = mediaPlayer.getDuration();
        music_img_seekbar.setMax(duration);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    //获取音乐当前进度
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    int seekBar = currentPosition;
                    Message obtain = Message.obtain();
                    obtain.obj = currentPosition;
                    handler.sendMessage(obtain);
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    //关闭时退出音乐
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying() && mediaPlayer != null) {
            mediaPlayer.pause();
            hom_lunbo_music_img_stop.setVisibility(View.VISIBLE);
            hom_music_img_starts.setVisibility(View.INVISIBLE);
            hom_lunbo_music_img_close.setImageResource(R.mipmap.abc_ic_go_search_api_mtrl_alpha);
        }
    }


}