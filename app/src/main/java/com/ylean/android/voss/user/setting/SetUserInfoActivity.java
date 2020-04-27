package com.ylean.android.voss.user.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ylean.android.voss.R;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 设置个人资料
 */
public class SetUserInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user);
    }
}
