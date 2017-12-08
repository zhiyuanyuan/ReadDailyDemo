package com.zhiyuan.readdailydemo.api;

import com.zhiyuan.readdailydemo.MyApp;
import com.zhiyuan.readdailydemo.util.StateUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/12/8.
 */

public class ApiRetofit {
    public static final String GANK_BASE_URL = "http://gank.io/api/";
    //每日一文 数据接口
    public static final String Daily_ESSAY_TODAY_URL = "https://interface.meiriyiwen.com/";
   ApiRetofit(){
       File httpCacheDirectory= new File(MyApp.mContext.getCacheDir(),"responses");
       int cacheSize=10*1024*1024;
       Cache cache=new Cache(httpCacheDirectory,cacheSize);
       Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR=chain ->{
           CacheControl.Builder cacheBuilder = new CacheControl.Builder();
           cacheBuilder.maxAge(0, TimeUnit.SECONDS);
           cacheBuilder.maxStale(365, TimeUnit.DAYS);
           CacheControl cacheControl = cacheBuilder.build();
           Request request = chain.request();
           if(!StateUtils.isNetWorkAvailable(MyApp.mContext)){
               request = request.newBuilder()
                       .cacheControl(cacheControl)
                       .build();
           }
           Response originalResponse = chain.proceed(request);
       };
   }

}
