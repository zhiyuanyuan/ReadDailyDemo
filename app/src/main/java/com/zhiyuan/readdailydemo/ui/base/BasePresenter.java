package com.zhiyuan.readdailydemo.ui.base;

import com.zhiyuan.readdailydemo.api.ApiFactory;
import com.zhiyuan.readdailydemo.api.DailyApi;
import com.zhiyuan.readdailydemo.api.GankApi;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by admin on 2017/12/8.
 */

public class BasePresenter<V> {
     protected Reference<V> mViewRef;
     public static final GankApi gankApi=ApiFactory.getGankApiSingleton();
     public static final DailyApi dailyApi=ApiFactory.getDailyApiSingleton();

     public void attachView(V view){
          mViewRef=new WeakReference<V>(view);
     }

     public V getView(){
         return mViewRef.get();
     }

     public boolean isViewAttached(){
          return mViewRef!=null&&mViewRef.get()!=null;
     }

     public void detachView(){
          if(mViewRef!=null){
               mViewRef.clear();
               mViewRef=null;
          }
     }


}
