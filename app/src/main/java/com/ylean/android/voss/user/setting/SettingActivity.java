package com.ylean.android.voss.user.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ylean.android.voss.R;
import com.zxdc.utils.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_version)
    TextView tvVersion;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_back, R.id.rel_user, R.id.rel_account, R.id.rel_abount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.rel_user:
                setClass(SetUserInfoActivity.class);
                break;
            case R.id.rel_account:
                break;
            case R.id.rel_abount:
                break;
            default:
                break;
        }
    }
}
