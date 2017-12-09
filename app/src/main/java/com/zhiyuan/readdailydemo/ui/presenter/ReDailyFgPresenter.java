package com.zhiyuan.readdailydemo.ui.presenter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.zhiyuan.readdailydemo.bean.EssayForDB;
import com.zhiyuan.readdailydemo.ui.base.BasePresenter;
import com.zhiyuan.readdailydemo.ui.view.IReDailyFgView;

import rx.Observable;
import rx.Observer;


/**
 * Created by admin on 2017/12/9.
 */

public class ReDailyFgPresenter extends BasePresenter<IReDailyFgView>{

    private  Context context;
    private IReDailyFgView mIReDailyFgView;
    private EssayForDB essay;
    private IReDailyFgView iReDailyFgView;
    private TextView title, author, content;
    private FloatingActionButton saveEssay;
    public ReDailyFgPresenter(Context context) {
        this.context = context;
    }
    public void getReDailyData(){
        mIReDailyFgView = getView();
        if(mIReDailyFgView!=null){
            title = iReDailyFgView.getTitleView();
            author = iReDailyFgView.getAuthorView();
            content = iReDailyFgView.getContentView();
            saveEssay = iReDailyFgView.getSaveEssaybtn();

            essay = new EssayForDB();
            Observable.create()
        }
    }
}
