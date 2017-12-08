package com.zhiyuan.readdailydemo.api;

import com.zhiyuan.readdailydemo.bean.GankData;
import com.zhiyuan.readdailydemo.bean.Meizhi;
import com.zhiyuan.readdailydemo.bean.Video;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by admin on 2017/12/8.
 */

public interface GankApi {
    @GET("data/福利/10/{page}")
    Observable<Meizhi> getMeizhiData(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getGankData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/休息视频/10/{page}")
    Observable<Video> getVideoData(@Path("page") int page);

}
