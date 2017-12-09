package com.zhiyuan.readdailydemo.ui.activity;

import com.zhiyuan.readdailydemo.R;
import com.zhiyuan.readdailydemo.ui.base.BasePresenter;
import com.zhiyuan.readdailydemo.ui.base.MVPBaseActivity;

/**
 * Created by admin on 2017/12/9.
 */

public class SplashActivity extends MVPBaseActivity {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }
}
