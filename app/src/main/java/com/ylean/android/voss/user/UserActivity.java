package com.ylean.android.voss.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.android.voss.R;
import com.ylean.android.voss.user.setting.SettingActivity;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人中心
 * Created by Administrator on 2020/4/26.
 */
public class UserActivity extends BaseActivity{

    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.img_member)
    ImageView imgMenber;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.img_setting,R.id.img_qrcode,R.id.tv_sign,R.id.tv_order,R.id.tv_dynamic,R.id.tv_integral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //设置
            case R.id.img_setting:
                 setClass(SettingActivity.class);
                  break;
            //二维码
            case R.id.img_qrcode:
                  break;
            //签到
            case R.id.tv_sign:
                  break;
            //我的订单
            case R.id.tv_order:
                 break;
             //我的动态
            case R.id.tv_dynamic:
                  break;
            //积分商城
            case R.id.tv_integral:
                 break;
              default:
                  break;

        }
    }
}
