package com.hongliang.baselibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongliang.baselibrary.httputil.AppConst;
import com.hongliang.baselibrary.httputil.BuildConfigs;
import com.hongliang.baselibrary.httputil.interceptor.CacheInterceptor;
import com.hongliang.baselibrary.httputil.interceptor.HeaderInterceptor;
import com.hongliang.baselibrary.httputil.interceptor.NetWorkInterceptor;
import com.hongliang.retrofitutils.RetrofitManage;
import com.hongliang.retrofitutils.converter.MyGsonConverterFactory;
import com.hongliang.retrofitutils.util.ExecutorCallAdapterFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class BaseApplication extends Application {
    private static Context instance;

    public static List<Activity> activities = new LinkedList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("TAG", "---BaseApplication");
        instance = this;

        Cache cache = new Cache(new File(BuildConfigs.PATH_CACHE), BuildConfigs.DEFAULT_CACHE_SIZE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new NetWorkInterceptor())
                .addInterceptor(new HeaderInterceptor())//设置Header
                .addInterceptor(new CacheInterceptor())
                .cache(cache)
                .connectTimeout(BuildConfigs.DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(BuildConfigs.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(BuildConfigs.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                .retryOnConnectionFailure(true);//错误重连

        OkHttpClient client = builder.build();
        Gson gson = new GsonBuilder().disableHtmlEscaping().enableComplexMapKeySerialization().create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConst.BASE_URL)
                .client(client)
                .addConverterFactory(MyGsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(ExecutorCallAdapterFactory.INSTANCE)
                .build();


        RetrofitManage.getInstents().addRetrofit(retrofit);
//传一个Api,再直接调用其方法

    }

    public static Context getInstance() {
        return instance;
    }


}
