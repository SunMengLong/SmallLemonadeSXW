package com.explem.smalllemonade.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import org.xutils.x;


/**
 * Created by Pooh on 2016/12/27.
 */

public class MyApplication extends Application{
    private static Context applicationContext;
    private static Handler handler;
    private static int mainId;
    private static Thread thread;
    public static boolean quanxuan=true;

    //定义显示隐藏的登陆界面的图片及文字
    public static int showLoginImg=0;
    public static int unShowLoginImg=1;

    //判断要显示的是哪一个
    public static boolean myFlag=false;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xutils
        x.Ext.init(this);
        x.Ext.setDebug(true);
        //得到上下文
        applicationContext = getApplicationContext();
        //Handler方法
        handler = new Handler();
        //线程号
        mainId = Process.myTid();
        //获取主线程
        thread = Thread.currentThread();
    }
    //上下文的方法
    public static Context getConText(){
        return applicationContext;
    }
    //得到hander的方法
    public static Handler getHandler(){
        return handler;
    }
    //得到线程号的方法
    public static int getMainId(){
        return mainId;
    }
    //得到主线程的方法
    public static Thread getMainThread(){
        return thread;
    }
}
