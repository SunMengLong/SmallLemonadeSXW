package com.explem.smalllemonade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    @Override
    public void onClick(View v) {

    }
}
