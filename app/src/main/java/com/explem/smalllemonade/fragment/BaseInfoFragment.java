package com.explem.smalllemonade.fragment;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.explem.smalllemonade.R;
import com.explem.smalllemonade.UapdateNameAcitvity;
import com.explem.smalllemonade.utils.CommonUtils;
import com.explem.smalllemonade.utils.StartUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by ${薛亚南}
 * on 2016/12/29 18：18.
 */

public class BaseInfoFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView img_baseicon;
    private TextView tv_baseBrDay, tv_BaseLoveState, tv_BaseName, tv_BaseStar, tv_BaseWork;
    private Calendar mCalendar;
    private int a;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.baseinfo_fragment, null);
        intiView();
        return view;
    }

    public void intiView() {
        view.findViewById(R.id.bf_reIcon).setOnClickListener(this);
        view.findViewById(R.id.bf_reBrDay).setOnClickListener(this);
        view.findViewById(R.id.bf_reLove).setOnClickListener(this);
        view.findViewById(R.id.bf_reName).setOnClickListener(this);
        view.findViewById(R.id.bf_reWork).setOnClickListener(this);
        //头像
        img_baseicon = (ImageView) view.findViewById(R.id.img_baseicon);
        //生日
        tv_baseBrDay = (TextView) view.findViewById(R.id.tv_BaseBrDay);
        //情感状态
        tv_BaseLoveState = (TextView) view.findViewById(R.id.tv_BaseLoveState);
        //姓名
        tv_BaseName = (TextView) view.findViewById(R.id.tv_BaseName);
        //星座
        tv_BaseStar = (TextView) view.findViewById(R.id.tv_BaseStar);
        //工作
        tv_BaseWork = (TextView) view.findViewById(R.id.tv_BaseWork);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //头像
            case R.id.bf_reIcon:
                popurView();
                break;
            //昵称
            case R.id.bf_reName:
                jump(0);
                break;
            //生日
            case R.id.bf_reBrDay:
                showDatePickerDialog();
                break;
            //情感状态
            case R.id.bf_reLove:
                jump(1);
                break;
            //工作
            case R.id.bf_reWork:
                jump(2);
                break;
        }

    }

    public void jump(int i) {
        Intent intent = new Intent(getActivity(), UapdateNameAcitvity.class);
        intent.putExtra("id", i);
        startActivity(intent);
    }

    private void showDatePickerDialog() {
        //获得日历的对象
        mCalendar = Calendar.getInstance();
        //创建一个DatePickerDialog的对象
        DatePickerDialog datedialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mCalendar.set(i, i1, i2);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                //  Toast.makeText(getActivity(), "" + format.format(mCalendar.getTime()), Toast.LENGTH_SHORT).show();
                tv_baseBrDay.setText(i + "." + (i1 + 1) + "." + i2);
                String starSeat = StartUtils.getStarSeat(i1 + 1, i2);
                tv_BaseStar.setText(starSeat);
            }
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        datedialog.show();
    }

    /**
     * by 张磊 调用照相机和相册
     */
    public void popurView() {
        final View popur_item = View.inflate(CommonUtils.getContext(), R.layout.my_icon_popur, null);
        final PopupWindow popupWindow = new PopupWindow(popur_item, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(img_baseicon, Gravity.CENTER, 0, 0);
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
        Button my_icon_camera = (Button) popur_item.findViewById(R.id.my_icon_camera);
        Button my_icon_photo = (Button) popur_item.findViewById(R.id.my_icon_photo);
        my_icon_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, Activity.DEFAULT_KEYS_DIALER);
                a = 1;
            }
        });
        my_icon_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
                a = 2;
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        switch (a) {
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
                    String str = null;
                    Date date = null;
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
                    date = new Date();
                    str = format.format(date);
                    String fileName = "/sdcard/myImage/" + str + ".jpg";
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
                        if (data != null) {
                            Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
                            img_baseicon.setImageBitmap(cameraBitmap);
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

        Glide.with(CommonUtils.getContext()).load(imagePath).into(img_baseicon);

    }
}
