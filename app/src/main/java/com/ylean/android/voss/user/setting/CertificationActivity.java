package com.ylean.android.voss.user.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.ylean.android.voss.R;
import com.ylean.android.voss.util.SelectPhoto;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CertificationActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.img_zm)
    ImageView imgZm;
    @BindView(R.id.img_fm)
    ImageView imgFm;
    @BindView(R.id.img_sc)
    ImageView imgSc;
    private File zmFile,fmFile,scFile;
    /**
     * 0：正面
     * 1：反面
     * 2：手持
     */
    private int type;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_back, R.id.img_zm, R.id.img_fm, R.id.img_sc, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.img_zm:
                type=0;
                showPhotoDialog();
                break;
            case R.id.img_fm:
                type=1;
                showPhotoDialog();
                break;
            case R.id.img_sc:
                type=2;
                showPhotoDialog();
                break;
            case R.id.tv_submit:
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
                SelectPhoto.selectPhoto(CertificationActivity.this,1);
            }
        });
        //从相册选择
        view.findViewById(R.id.tv_photo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                SelectPhoto.selectPhoto(CertificationActivity.this,2);
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
                switch (type){
                    case 0:
                         zmFile= new File(SelectPhoto.crop);
                         Glide.with(CertificationActivity.this).load(Uri.fromFile(zmFile)).into(imgZm);
                         break;
                    case 1:
                        fmFile= new File(SelectPhoto.crop);
                        Glide.with(CertificationActivity.this).load(Uri.fromFile(fmFile)).into(imgFm);
                        break;
                    case 2:
                        scFile= new File(SelectPhoto.crop);
                        Glide.with(CertificationActivity.this).load(Uri.fromFile(scFile)).into(imgSc);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
