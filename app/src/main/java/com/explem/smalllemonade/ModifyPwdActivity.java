package com.explem.smalllemonade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.smalllemonade.bean.WangjiBean;
import com.explem.smalllemonade.utils.BaseDate;
import com.explem.smalllemonade.view.ShowingPage;
import com.google.gson.Gson;

public class ModifyPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_pwd_right;
    private TextView mf_pwd_tv1;
    private TextView mf_pwd_tv2;
    private TextView mf_pwd_tv3;
    private EditText mf_old_pwd;
    private EditText mf_new_pwd;
    private EditText mf_next_pwd;
    private String old_pwd;
    private String new_pwd;
    private String next_pwd;
    private String phonNum;
    private  String papa="http://114.112.104.151:8203/LvScore_Service/visit/setUserLoginPassword.do";
    private String pwdNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
        initView();

        title_pwd_right.setOnClickListener(this);
    }

    private void initView() {
        title_pwd_right = (TextView) findViewById(R.id.title_pwd_right);
        mf_pwd_tv1 = (TextView) findViewById(R.id.mf_pwd_tv1);
        mf_pwd_tv2 = (TextView) findViewById(R.id.mf_pwd_tv2);
        mf_pwd_tv3 = (TextView) findViewById(R.id.mf_pwd_tv3);
        mf_old_pwd = (EditText) findViewById(R.id.mf_old_pwd);
        mf_new_pwd = (EditText) findViewById(R.id.mf_new_pwd);
        mf_next_pwd = (EditText) findViewById(R.id.mf_next_pwd);


    }

    private void initPwd() {
        old_pwd = mf_old_pwd.getText().toString().trim();
        new_pwd = mf_new_pwd.getText().toString().trim();
        next_pwd = mf_next_pwd.getText().toString().trim();
        phonNum = WangJiActivity.name;
        pwdNum = LoginActivity.password;
    }

    @Override
    public void onClick(View v) {
        initPwd();
        if(!old_pwd.equals(pwdNum)){
            mf_pwd_tv1.setText("密码错误！请重新输入");
        }else {
            mf_pwd_tv1.setText("");
            if(!(new_pwd.length()>=6)){
                mf_pwd_tv2.setText("密码长度必须大于6");
            }else{
                mf_pwd_tv2.setText("");
                if(!(new_pwd.equals(next_pwd))){
                    mf_pwd_tv3.setText("两次密码不相同！请检查");
                }else{
                    mf_pwd_tv3.setText("");
                    tijiao();
                }
            }
        }
    }
    private void tijiao() {
        new BaseDate(ModifyPwdActivity.this) {
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
                        finish();
                        break;
                    case "error":
                        Toast.makeText(ModifyPwdActivity.this, baocun.data.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }.getDate(papa,"telNum="+phonNum+"&password="+mf_new_pwd.getText().toString().trim(),2,0);
}
}
