package com.explem.smalllemonade.community.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.explem.smalllemonade.R;
import com.explem.smalllemonade.base.BaseFragment;
import com.explem.smalllemonade.community.CategoryDetailActivity;
import com.explem.smalllemonade.fragment.MineFragment;
import com.explem.smalllemonade.utils.CommonUtils;
import com.explem.smalllemonade.view.ShowingPage;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class SubCommunityFragment_Category extends BaseFragment implements View.OnClickListener {
    @Override
    protected void onload() {
        SubCommunityFragment_Category.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.subcommunityfragment_category);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("123456","能不能打个log?");
        getActivity().findViewById(R.id.subcommunityfragment_first).setOnClickListener(this);
        getActivity().findViewById(R.id.subcommunityfragment_second).setOnClickListener(this);
        getActivity().findViewById(R.id.subcommunityfragment_third).setOnClickListener(this);
        getActivity().findViewById(R.id.subcommunityfragment_fourth).setOnClickListener(this);
        getActivity().findViewById(R.id.subcommunityfragment_five).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.subcommunityfragment_first:
                Toast.makeText(getActivity(),"first", Toast.LENGTH_SHORT).show();
                gotoActivity(CategoryDetailActivity.class,"first");
                break;
            case  R.id.subcommunityfragment_second:
                Toast.makeText(getActivity(),"second", Toast.LENGTH_SHORT).show();
                gotoActivity(CategoryDetailActivity.class,"second");
                break;
            case  R.id.subcommunityfragment_third:
                Toast.makeText(getActivity(),"third", Toast.LENGTH_SHORT).show();
                gotoActivity(CategoryDetailActivity.class,"third");
                break;
            case  R.id.subcommunityfragment_fourth:
                Toast.makeText(getActivity(),"fourth", Toast.LENGTH_SHORT).show();
                gotoActivity(CategoryDetailActivity.class,"fourth");
                break;
            case  R.id.subcommunityfragment_five:
                Toast.makeText(getActivity(),"five", Toast.LENGTH_SHORT).show();
                gotoActivity(CategoryDetailActivity.class,"five");
                break;
        }

    }

    public void gotoActivity(Class c,String flag){
        Intent intent = new Intent(getActivity(),c);
        intent.putExtra("flag",flag);
        startActivity(intent);
    }
}
