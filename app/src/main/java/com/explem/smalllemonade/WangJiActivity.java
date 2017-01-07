package com.explem.smalllemonade;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.explem.smalllemonade.bean.WangjiBean;
import com.explem.smalllemonade.bean.WcoBean;
import com.explem.smalllemonade.bean.YanZhengBean;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;


public class WangJiActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView  title_tvtitle;
    private  EditText  et_hao;
    private  EditText  et_xinmima;
    private  EditText   et_yanzheng;
    private CardView cd_yanzheng;
    private CardView cd_tijiao;
    private String name;
    private String pat = "http://114.112.104.151:8203/LvScore_Service/visit/user_getverificationcode.do";
    private String args = "telNum=" + name;
    private  String path1="http://114.112.104.151:8203/LvScore_Service/visit/user_checkVerificationCode.do";
    //    private  String args1="telNum="+name+"&verCode="+et_yanzheng;
    //http://114.112.104.151:8203/LvScore_Service/visit/user_register.do?telNum=18500704988&name=godboy&password=123456
   // private  String pa="http://114.112.104.151:8203/LvScore_Service/visit/user_register.do";
    private  String papa="http://114.112.104.151:8203/LvScore_Service/visit/setUserLoginPassword.do";
    //http://114.112.104.151:8203/LvScore_Service/visit/setUserLoginPassword.do?telNum=18500704988&userId=10124&password=12345678

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_ji);
        title_tvtitle=(TextView)findViewById(R.id.title_tvtitle);
        et_hao=(EditText)findViewById(R.id.et_hao);
        et_xinmima=(EditText)findViewById(R.id.et_xinmima);
        et_yanzheng=(EditText)findViewById(R.id.et_yanzheng);

        cd_yanzheng=(CardView) findViewById(R.id.cd_yanzheng);
        cd_tijiao=(CardView)findViewById(R.id.cd_tijiao);
        title_tvtitle.setText("忘记密码");

        cd_yanzheng.setOnClickListener(this);
        cd_tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cd_yanzheng:
                name = et_hao.getText().toString().trim();
                args = "telNum=" + name;
                if (name.equals("")) {
                    // 弹出消息框
                    new AlertDialog.Builder(WangJiActivity.this).setTitle("错误")
                            .setMessage("请输入手机号").setPositiveButton("确定", null)
                            .show();
                } else {
                    // 1. 通过规则判断手机号
                    if (!judgePhoneNums(name)) {
                        return;
                    }else {
                        yanzheng();
                    }
                }
                break;
            case R.id.cd_tijiao:
                // 1. 判断密码为6位
                String mima = et_xinmima.getText().toString().trim();
                if (!(mima.length()>=6) ){
                    Toast.makeText(this, "密码是6位哦！", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    tijiao();
                }
                break;
        }
    }
    private void tijiao() {
        new BaseDate(WangJiActivity.this) {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
            }
            @Override
            public void setResultData(String data) {
                Gson gson= new Gson();
                WangjiBean baocun = gson.fromJson(data, WangjiBean.class);
                String   status1 = baocun.status;
                switch (status1){
                    case "ok":
                        Toast.makeText(WangJiActivity.this, ")))))))))！", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(WangJiActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;
                    case "error":
                        Toast.makeText(WangJiActivity.this, baocun.data.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }.getDate(papa,"telNum="+et_hao.getText().toString().trim()+"&password="+et_xinmima.getText().toString().trim(),2,0);

    }
    private void yanzheng(){
        new BaseDate(WangJiActivity.this) {
            @Override
            protected void setResultError(ShowingPage.StateType stateLoadError) {
            }
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                WcoBean   wcoBean = gson.fromJson(data,WcoBean.class);
                String status = wcoBean.status;
                switch (status) {
                    case "ok":
                        Toast.makeText(WangJiActivity.this, "成功！", Toast.LENGTH_SHORT).show();
                        wancheng();
                        break;
                    case "error":
                        Toast.makeText(WangJiActivity.this, "参数无效！", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }.getDate(pat, args, 8, 0);
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
        new BaseDate(WangJiActivity.this) {
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
                        Toast.makeText(WangJiActivity.this, "haha！", Toast.LENGTH_SHORT).show();
                        break;
                    case "error":
                        Toast.makeText(WangJiActivity.this, yanZhengBean.data.message, Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        }.getDate(path1,"telNum="+et_hao.getText().toString().trim()+"&verCode="+et_yanzheng.getText().toString().trim(),2,0);
    }
}
