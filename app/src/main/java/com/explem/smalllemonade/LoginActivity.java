package com.explem.smalllemonade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.smalllemonade.bean.LogBean;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_logo;
    private TextView wangji_tv;
    private ImageView weixin_iv;
    private TextView zhuce_tv;
    private CardView cv_log;
    // 手机号和密码
    private EditText edname;
    private EditText edpassword;
    String status;
    String message;
    String name;
   public static String password;
    LogBean logBean;
    //http://114.112.104.151:8203/LvScore_Service/visit/user_checkVerificationCode.
  //  do?telNum=18500704987&verCode=730219
    //4db0f304e7c32bfd2e17bb38c1780596
    private  String path="http://114.112.104.151:8203/LvScore_Service/visit/user_login.do";
    private  String args="telNum="+name+"&password="+password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        wangji_tv=(TextView)findViewById(R.id.wangji_tv);
        weixin_iv=(ImageView)findViewById(R.id.weixin_iv);
        zhuce_tv=(TextView) findViewById(R.id.zhuce_tv);
        cv_log=(CardView) findViewById(R.id.cv_log);
        edname=(EditText) findViewById(R.id.phone_num);
        edpassword=(EditText) findViewById(R.id.et);
        //监听
        wangji_tv.setOnClickListener(this);
        weixin_iv.setOnClickListener(this);
        zhuce_tv.setOnClickListener(this);
        cv_log.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.cv_log:
                name = edname.getText().toString().trim();
                password = edpassword.getText().toString().trim();
                args="telNum="+name+"&password="+password;
                if (name.equals("") || password.equals("")) {
                    // 弹出消息框
                    new AlertDialog.Builder(LoginActivity.this).setTitle("错误")
                            .setMessage("手机号或密码不能空").setPositiveButton("确定", null)
                            .show();
                } else {
                    // 1. 通过规则判断手机号
                    if (!judgePhoneNums(name)) {
                        return;
                    }else{
                        login();
                    }
                }
                break;
            case  R.id.wangji_tv:
                startActivity(new Intent(LoginActivity.this,WangJiActivity.class));
                break;
            case  R.id.weixin_iv:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                break;
            case  R.id.zhuce_tv:
                startActivity(new Intent(LoginActivity.this,ZhuceActivity.class));
                break;
        }
    }

    private void login() {
        new BaseDate(LoginActivity.this) {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
            }
            @Override
            public void setResultData(String data) {
                Gson gson= new Gson();
                 logBean = gson.fromJson(data, LogBean.class);
                  status = logBean.status;
                switch (status){
                    case "ok":
                        Toast.makeText(LoginActivity.this,"成功登陆",Toast.LENGTH_LONG).show();
                        // startActivity(new Intent(LoginActivity.this,MainActivity.class));
                       startActivity(new Intent(LoginActivity.this,ViewDemoActivity.class));
                    case "error":
                        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                }
            }
        }.getDate(path,args,8,0);
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
        Toast.makeText(this, "手机号码输入有误！",Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 判断一个字符串的位数
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

}
