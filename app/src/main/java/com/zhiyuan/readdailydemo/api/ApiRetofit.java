package com.zhiyuan.readdailydemo.api;

import com.zhiyuan.readdailydemo.MyApp;
import com.zhiyuan.readdailydemo.util.StateUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/12/8.
 */

public class ApiRetofit {
    public static final String GANK_BASE_URL = "http://gank.io/api/";
    //每日一文 数据接口
    public static final String Daily_ESSAY_TODAY_URL = "https://interface.meiriyiwen.com/";

    public GankApi GankApiService;
    public DailyApi DailyApiService;

    public GankApi getGankApiService() {
        return GankApiService;
    }
    public DailyApi getDailyApiService() {
        return DailyApiService;
    }
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
          if(StateUtils.isNetWorkAvailable(MyApp.mContext)){
              int maxAge = 0; // read from cache
              return originalResponse.newBuilder()
                      .removeHeader("Pragma")
                      .header("Cache-Control", "public ,max-age=" + maxAge)
                      .build();
          }else {
              int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
              return originalResponse.newBuilder()
                      .removeHeader("Pragma")
                      .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                      .build();
          }
       };

       OkHttpClient client = new OkHttpClient.Builder()
               .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
               .cache(cache).build();

       Retrofit retrofit_gank = new Retrofit.Builder()
               .baseUrl(GANK_BASE_URL)
               .client(client)
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .build();
       Retrofit retrofit_daily = new Retrofit.Builder()
               .baseUrl(Daily_ESSAY_TODAY_URL)
               .client(client)
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .build();


       GankApiService = retrofit_gank.create(GankApi.class);
       DailyApiService = retrofit_daily.create(DailyApi.class);
   }

}
