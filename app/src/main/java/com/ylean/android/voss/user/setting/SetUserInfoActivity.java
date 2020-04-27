package com.ylean.android.voss.user.setting;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.android.voss.R;
import com.ylean.android.voss.util.SelectPhoto;
import com.ylean.android.voss.view.CycleWheelView;
import com.ylean.android.voss.view.SelectTimeDialog;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.view.CircleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置个人资料
 */
public class SetUserInfoActivity extends BaseActivity {

    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.et_nickName)
    EditText etNickName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.et_wx)
    EditText etWx;
    @BindView(R.id.et_email)
    EditText etEmail;
    private File imgFile;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_back, R.id.img_head, R.id.tv_name, R.id.tv_sex, R.id.tv_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择头像
            case R.id.img_head:
                showPhotoDialog();
                break;
            //实名认证
            case R.id.tv_name:
                setClass(CertificationActivity.class);
                break;
            //选择性别
            case R.id.tv_sex:
                selectSex();
                break;
            //选择生日
            case R.id.tv_birthday:
                SelectTimeDialog selectTimeDialog=new SelectTimeDialog(this);
                selectTimeDialog.show();
                break;
            default:
                break;
        }
    }


    /**
     * 展示选择图片的弹框
     */
    private void showPhotoDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_photo, null);
        final PopupWindow popupWindow = DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        //拍照
        view.findViewById(R.id.tv_picture).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                SelectPhoto.selectPhoto(SetUserInfoActivity.this,1);
            }
        });
        //从相册选择
        view.findViewById(R.id.tv_photo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                SelectPhoto.selectPhoto(SetUserInfoActivity.this,2);
            }
        });
        view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回拍照图片
            case SelectPhoto.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(SelectPhoto.pai);
                    if (tempFile.isFile()) {
                        SelectPhoto.cropRawPhoto(Uri.fromFile(tempFile),this);
                    }
                }
                break;
            //返回相册选择图片
            case SelectPhoto.CODE_GALLERY_REQUEST:
                if (data != null) {
                    SelectPhoto.cropRawPhoto(data.getData(),this);
                }
                break;
            //返回裁剪的图片
            case SelectPhoto.CODE_RESULT_REQUEST:
                imgFile=new File(SelectPhoto.crop);
                Glide.with(SetUserInfoActivity.this).load(Uri.fromFile(imgFile)).into(imgHead);
                break;
            default:
                break;
        }
    }


    /**
     *
     * 选择性别
     */
    public void selectSex(){
        View view= LayoutInflater.from(activity).inflate(R.layout.wheel_select,null);
        final PopupWindow popupWindow=DialogUtil.showPopWindow(view);
        popupWindow .showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
        try{
            CycleWheelView wheel=view.findViewById(R.id.wheel);
            List<String> list=new ArrayList<>();
            list.add("男");
            list.add("女");
            wheel.setLabels(list);
            wheel.setSelection(0);
            wheel.setWheelSize(5);
            wheel.setCycleEnable(false);
            wheel.setAlphaGradual(0.5f);
            wheel.setDivider(Color.parseColor("#abcdef"),1);
            wheel.setSolid(Color.WHITE,Color.WHITE);
            wheel.setLabelColor(Color.GRAY);
            wheel.setLabelSelectColor(Color.BLACK);
            wheel.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
                public void onItemSelected(int position, String label) {
                    tvSex.setText(label);
                }
            });

            view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popupWindow.dismiss();
                    tvSex.setText(null);
                }
            });
            view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
