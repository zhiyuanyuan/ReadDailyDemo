package com.zhiyuan.readdailydemo.api;

/**
 * Created by admin on 2017/12/9.
 */

public class ApiFactory {

    protected static final Object monitor=new Object();
    static GankApi gankApiSingleton = null;
    static DailyApi dailyApiSingleton = null;

    public static GankApi getGankApiSingleton(){
        synchronized(monitor){
            if(gankApiSingleton==null){
                return new ApiRetofit().getGankApiService();
            }
            return gankApiSingleton;
        }
    }

    public static DailyApi getDailyApiSingleton(){
        synchronized (monitor){
            if(dailyApiSingleton==null){
                return new ApiRetofit().getDailyApiService();
            }
            return dailyApiSingleton;
        }
    }
}
