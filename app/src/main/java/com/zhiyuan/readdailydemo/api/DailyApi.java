package com.zhiyuan.readdailydemo.api;

import com.zhiyuan.readdailydemo.bean.Essay;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by admin on 2017/12/8.
 */

public interface DailyApi {
    @GET("article/day?dev=1")
    Observable<Essay> getDailyEssay();
    @GET("article/random?dev=2")
    Observable<Essay> getDailyRssayRandom();
}
