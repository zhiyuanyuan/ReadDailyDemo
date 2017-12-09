package com.zhiyuan.readdailydemo.ui.presenter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyuan.readdailydemo.R;
import com.zhiyuan.readdailydemo.bean.EssayForDB;
import com.zhiyuan.readdailydemo.ui.base.BasePresenter;
import com.zhiyuan.readdailydemo.ui.view.IReDailyFgView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


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
            Observable.create((Observable.OnSubscribe<Document>) subscriber -> {
                try {
                    Document document= Jsoup.connect("https://meiriyiwen.com/random").get();
                    subscriber.onNext(document);
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onNext(null);
                    loadError(e);
                    iReDailyFgView.setDataRefresh(false);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(document -> {
                Element docContent = document.getElementById("article_show");

                essay.setTitle(document.select("h1").text());
                essay.setAuthor(document.getElementsByClass("article_author").text());
                essay.setContent(document.getElementsByClass("article_text").toString());
                essay.setDigest(document.getElementsByClass("article_text").text().substring(0, 48));

                // str = docContent.text();
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(document -> {
                displayEssay(essay, iReDailyFgView, title, author, content);
                iReDailyFgView.setDataRefresh(false);
            });
        }
    }

    private void displayEssay(EssayForDB essay, IReDailyFgView iReDailyFgView, TextView title, TextView author, TextView content) {

    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.loadError, Toast.LENGTH_SHORT).show();
        iReDailyFgView.setDataRefresh(false);
    }
}
