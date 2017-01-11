package com.explem.smalllemonade.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.MineJumpActivity;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.base.BaseFragment;
import com.explem.smalllemonade.utils.CommonUtils;
import com.explem.smalllemonade.view.ShowingPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.explem.smalllemonade.R.id.mf_reBaseInfo;

/**
 * Created by Pooh on 2016/12/27.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private View mineView;
    private ImageView mf_icon, mf_ivSex;
    private TextView mf_tvName, mf_tvNum;
    private Button my_icon_camera;
    private Button my_icon_photo;
    int a=0;


    @Override
    protected void onload() {
        //设置布局
        MineFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {
        mineView = CommonUtils.inflate(R.layout.fragment_mine);
        initView();
        return mineView;
    }

    private void initView() {
        //头像
        mf_icon = (ImageView) mineView.findViewById(R.id.mf_icon);
        //昵称
        mf_tvName = (TextView) mineView.findViewById(R.id.mf_tvName);
        //性别
        mf_ivSex = (ImageView) mineView.findViewById(R.id.mf_ivSex);
        //关注数量
        mf_tvNum = (TextView) mineView.findViewById(R.id.mf_tvNum);
        mineView.findViewById(mf_reBaseInfo).setOnClickListener(this);
        mineView.findViewById(R.id.mf_reMine).setOnClickListener(this);
        mineView.findViewById(R.id.mf_reFeed).setOnClickListener(this);
        mineView.findViewById(R.id.mf_reSeting).setOnClickListener(this);
        mf_icon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //基本资料
            case R.id.mf_reBaseInfo:
                jump(0);
                break;
            //我的帖子
            case R.id.mf_reMine:
                jump(1);
                break;
            //意见反馈
            case R.id.mf_reFeed:
                jump(2);
                break;
            //设置
            case R.id.mf_reSeting:
                jump(3);
                break;
            //置头像---通过照相或者相册获取
            case R.id.mf_icon:
                popurView();
                break;
        }

    }

    /**
     * by 张磊 调用照相机和相册
     */
    public void popurView() {
        final View popur_item=View.inflate(CommonUtils.getContext(), R.layout.my_icon_popur,null);
        final PopupWindow popupWindow = new PopupWindow(popur_item, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(mf_icon, Gravity.CENTER,0,0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        my_icon_camera = (Button) popur_item.findViewById(R.id.my_icon_camera);
        my_icon_photo = (Button) popur_item.findViewById(R.id.my_icon_photo);
        my_icon_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE );
                startActivityForResult(intent,Activity.DEFAULT_KEYS_DIALER);
                a=1;
            }
        });
        my_icon_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
                a=2;
            }
        });
    }

    public void jump(int id) {
        Intent intent = new Intent(getActivity(), MineJumpActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
            switch (a){
                case 1:
                    if (resultCode == Activity.RESULT_OK) {

                        String sdStatus = Environment.getExternalStorageState();
                        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                            return;
                        }
                        Bundle bundle = data.getExtras();
                        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
                        FileOutputStream b = null;
                        File file = new File("/sdcard/myImage/");
                        file.mkdirs();// 创建文件夹，名称为myimage

                        //照片的命名，目标文件夹下，以当前时间数字串为名称，即可确保每张照片名称不相同。
                        String str=null;
                        Date date=null;
                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
                        date =new Date();
                        str=format.format(date);
                        String fileName = "/sdcard/myImage/"+str+".jpg";
                        try {
                            b = new FileOutputStream(fileName);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                b.flush();
                                b.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (data!= null) {
                                Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
                                mf_icon.setImageBitmap(cameraBitmap);
                            }
                        }
                    }
                    break;
                case 2:
                    if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumns = {MediaStore.Images.Media.DATA};
                        Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                        c.moveToFirst();
                        int columnIndex = c.getColumnIndex(filePathColumns[0]);
                        String imagePath = c.getString(columnIndex);
                        showImage(imagePath);
                        c.close();
                    }
                    break;
            }

    }

    private void showImage(String imagePath) {

        Glide.with(CommonUtils.getContext()).load(imagePath).into(mf_icon);

    }



}
