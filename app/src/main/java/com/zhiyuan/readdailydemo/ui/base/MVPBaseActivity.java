package com.zhiyuan.readdailydemo.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import com.zhiyuan.readdailydemo.R;

/**
 * Created by admin on 2017/12/8.
 */

public abstract class MVPBaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    protected T presenter;
    protected AppBarLayout mAppBar;
    protected Toolbar mToolbar;
    private SwipeRefreshLayout mRefreshLayout;
    private boolean mIsRequestDataRefresh = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(createPresenter()!=null){
            presenter=createPresenter();
            presenter.attachView((V) this);
        }
        setContentView(provideContentViewId());
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mAppBar!=null&&mToolbar!=null){
            setSupportActionBar(mToolbar);
            if(canBack()){
                ActionBar actionBar = getSupportActionBar();
                if(actionBar!=null){
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    mAppBar.setElevation(10.6f);//Z轴浮动
                }
            }
        }
        if (isSetRefresh()) {
            setupSwipeRefresh();
        }
    }

    private void setupSwipeRefresh() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        if (mRefreshLayout != null) {
            mRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
                    R.color.refresh_progress_2, R.color.refresh_progress_3);
            mRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
            mRefreshLayout.setOnRefreshListener(this::requestDataRefresh);
        }
    }

    public void requestDataRefresh() {
        mIsRequestDataRefresh = true;
    }
    public void setRefresh(boolean requestDataRefresh) {
        if (mRefreshLayout == null) {
            return;
        }
        if (!requestDataRefresh) {
            mIsRequestDataRefresh = false;
            mRefreshLayout.postDelayed(() -> {
                if (mRefreshLayout != null) {
                    mRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        } else {
            mRefreshLayout.setRefreshing(true);
        }
    }
    protected abstract T createPresenter() ;
    abstract protected int provideContentViewId();//用于引入布局文件
    /**
     * 判断当前 Activity 是否允许返回
     * 主界面不允许返回，次级界面允许返回
     * @return false
     */
    public boolean canBack() {
        return false;
    }

    public Boolean isSetRefresh() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null) {
            presenter.detachView();
        }

    }
}
