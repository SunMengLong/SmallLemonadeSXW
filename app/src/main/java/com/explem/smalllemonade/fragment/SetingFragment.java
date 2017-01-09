package com.explem.smalllemonade.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.explem.smalllemonade.InviteActivity;
import com.explem.smalllemonade.ModifyPwdActivity;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.utils.CommonUtils;

/**
 * Created by ${薛亚南}
 * on 2016/12/29 18：18.
 */

public class SetingFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout mf_modify_pwd;
    private RelativeLayout mf_invite_friend;
    private RelativeLayout mf_about_mine;
    private RelativeLayout mf_version_update;
    private Button concle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=View.inflate(CommonUtils.getContext(), R.layout.fragment_seting,null);
        //修改密码
        mf_modify_pwd = (RelativeLayout) view.findViewById(R.id.mf_modify_pwd);
        //邀请好友
        mf_invite_friend = (RelativeLayout) view.findViewById(R.id.mf_invite_friend);
        //关于我们
        mf_about_mine = (RelativeLayout) view.findViewById(R.id.mf_about_mine);
        //版本更新
        mf_version_update = (RelativeLayout) view.findViewById(R.id.mf_version_update);
        //退出登陆
        concle = (Button) view.findViewById(R.id.concle);

        mf_modify_pwd.setOnClickListener(this);
        mf_invite_friend.setOnClickListener(this);
        mf_about_mine.setOnClickListener(this);
        mf_version_update.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //修改密码
            case R.id.mf_modify_pwd:
                intent(ModifyPwdActivity.class);
                break;
            //邀请好友
            case R.id.mf_invite_friend:
                intent(InviteActivity.class);
                break;
            //关于我们
            case R.id.mf_about_mine:
                break;
            //版本更新
            case R.id.mf_version_update:
                break;
            //退出登陆
            case R.id.concle:
                break;
        }
    }
    public void intent(Class c){
        Intent intent=new Intent(getContext(), c);
        startActivity(intent);
    }
}
