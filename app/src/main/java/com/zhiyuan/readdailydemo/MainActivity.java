package com.zhiyuan.readdailydemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.zhiyuan.readdailydemo.ui.base.BasePresenter;
import com.zhiyuan.readdailydemo.ui.base.MVPBaseActivity;
import com.zhiyuan.readdailydemo.ui.fragment.ReDailyFragment;

public class MainActivity extends MVPBaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences sp;
    private DrawerLayout mainDrawLayout;
    private NavigationView leftDraw;
    private FrameLayout contentFrame;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences(MyApp.SP_NAME, Context.MODE_PRIVATE);
        mainDrawLayout = (DrawerLayout) findViewById(R.id.main_draw_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        leftDraw = (NavigationView) findViewById(R.id.left_draw);
        contentFrame = (FrameLayout) findViewById(R.id.content_frame);
        initDrawerView();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new ReDailyFragment()).commit();
    }

    private void initDrawerView() {
        toggle = new ActionBarDrawerToggle(this, mainDrawLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mainDrawLayout.addDrawerListener(toggle);

        leftDraw.setNavigationItemSelectedListener(this);
        setTitle(getString(R.string.app_name));
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
