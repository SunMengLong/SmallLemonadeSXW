package com.explem.smalllemonade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.smalllemonade.bean.Baocun;
import com.explem.smalllemonade.bean.CoderBean;
import com.explem.smalllemonade.bean.YanZhengBean;
import com.explem.smalllemonade.tiaozhuan.TiaoZhuan;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;

public class ZhuceActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title_tvtitle;
    private TextView xiexi_tv;
    private String path = "http://www.yulin520.com/a2a/h/i/yulin/agreement";
    private CardView cv_huoqu;
    private EditText   et_yanzheng;
    private EditText et_hao;
    private TextView tv_huoqu;
    private EditText et_mima;
    private String name;
    private String pat = "http://114.112.104.151:8203/LvScore_Service/visit/user_getverificationcode.do";
    private String args = "telNum=" + name;
    private  String path1="http://114.112.104.151:8203/LvScore_Service/visit/user_checkVerificationCode.do";
//    private  String args1="telNum="+name+"&verCode="+et_yanzheng;
    //http://114.112.104.151:8203/LvScore_Service/visit/user_register.do?telNum=18500704988&name=godboy&password=123456
    private  String pa="http://114.112.104.151:8203/LvScore_Service/visit/user_register.do";
    int i = 60;
    private CardView cd_wancheng;
    private CoderBean coderBean;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        title_tvtitle = (TextView) findViewById(R.id.title_tvtitle);
        cd_wancheng = (CardView) findViewById(R.id.cd_wancheng);
        et_hao = (EditText) findViewById(R.id.et_hao);
        et_mima = (EditText) findViewById(R.id.et_mima);
        xiexi_tv = (TextView) findViewById(R.id.xiexi_tv);
        cv_huoqu = (CardView) findViewById(R.id.cv_huoqu);
        tv_huoqu = (TextView) findViewById(R.id.tv_huoqu);
        et_yanzheng = (EditText) findViewById(R.id.et_yanzheng);
        cv_huoqu.setOnClickListener(this);
        xiexi_tv.setOnClickListener(this);
        cd_wancheng.setOnClickListener(this);
        title_tvtitle.setText("注册");
    }
//13363087933
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiexi_tv:
                TiaoZhuan.tiaoZhuan(ZhuceActivity.this, path);
                break;
            case R.id.cv_huoqu:
                name = et_hao.getText().toString().trim();
                args = "telNum=" + name;
                if (name.equals("")) {
                    // 弹出消息框
                    new AlertDialog.Builder(ZhuceActivity.this).setTitle("错误")
                            .setMessage("请输入手机号").setPositiveButton("确定", null)
                            .show();
                } else {
                    // 1. 通过规则判断手机号
                    if (!judgePhoneNums(name)) {
                        return;
                    }
                }
          /*  // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                cv_huoqu.setClickable(false);
                tv_huoqu.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            handler.obtainMessage();
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();*/
                new BaseDate(ZhuceActivity.this) {
                    @Override
                    protected void setResultError(ShowingPage.StateType stateLoadError) {
                    }
                    @Override
                    public void setResultData(String data) {
                        Gson gson = new Gson();
                        coderBean = gson.fromJson(data, CoderBean.class);
                        String status = coderBean.status;
                        switch (status) {
                            case "ok":
                                Toast.makeText(ZhuceActivity.this, "成功！", Toast.LENGTH_SHORT).show();
                                wancheng();
                                break;
                            case "error":
                                Toast.makeText(ZhuceActivity.this, "参数无效！", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }.getDate(pat, args, 8, 0);
                break;
            case R.id.cd_wancheng:
                // 1. 判断密码为6位
                String mima = et_mima.getText().toString().trim();
                if (!(mima.length()>=6) ){
                    Toast.makeText(this, "密码是6位哦！", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    baocun();
                }
                break;
        }
    }

    private void baocun() {
        new BaseDate(ZhuceActivity.this) {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
            }
            @Override
            public void setResultData(String data) {
                Gson gson= new Gson();
                Baocun baocun = gson.fromJson(data, Baocun.class);
                String   status1 = baocun.status;
                String image_url = baocun.data.image_url;
                String userId = baocun.data.userId;
                String telNum = baocun.data.telNum;
                String password = baocun.data.password;
                switch (status1){
                    case "ok":
                        Toast.makeText(ZhuceActivity.this, ")))))))))！", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ZhuceActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;
                    case "error":
                        Toast.makeText(ZhuceActivity.this, "************", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        }.getDate(pa,"telNum="+et_hao.getText().toString().trim()+"&name=godboy&password="+et_mima.getText().toString().trim(),2,0);

    }


    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
        return false;
    }


    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }
    public void wancheng(){
        new BaseDate(ZhuceActivity.this) {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
            }
            @Override
            public void setResultData(String data) {
                Gson gson= new Gson();
                YanZhengBean yanZhengBean = gson.fromJson(data, YanZhengBean.class);
                String   status1 = yanZhengBean.status;
                switch (status1){
                    case "ok":
                        Toast.makeText(ZhuceActivity.this, "haha！", Toast.LENGTH_SHORT).show();
                        break;
                    case "error":
                        Toast.makeText(ZhuceActivity.this, yanZhengBean.data.message, Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        }.getDate(path1,"telNum="+et_hao.getText().toString().trim()+"&verCode="+et_yanzheng.getText().toString().trim(),2,0);
    }
}
