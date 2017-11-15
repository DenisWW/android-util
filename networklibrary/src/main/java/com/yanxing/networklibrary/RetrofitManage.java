package com.yanxing.networklibrary;


import android.content.Context;

import com.yanxing.networklibrary.intercepter.CacheInterceptor;
import com.yanxing.networklibrary.intercepter.ParameterInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lishuangxiang on 2017/04/01.
 */
public class RetrofitManage {

    private Retrofit.Builder mRetrofitBuilder;
    private OkHttpClient.Builder mOkHttpClientBuilder;

    private RetrofitManage() {
        mOkHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS);
        mRetrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public static RetrofitManage getInstance() {
        return SingletonHolder.retrofitManage;

    }

    private static class SingletonHolder {
        private static final RetrofitManage retrofitManage = new RetrofitManage();
    }

    /**
     * 初始化Retrofit
     *
     * @param baseUrl
     * @param log     true打印请求参数和返回数据
     */
    public synchronized void init(String baseUrl, boolean log) {
        mRetrofitBuilder
                .baseUrl(baseUrl)
                .client(mOkHttpClientBuilder.addInterceptor(new ParameterInterceptor(log)).build());
    }

    /**
     * 自定义okHttpClient
     *
     * @param okHttpClient
     */
    public void setHttpClient(OkHttpClient okHttpClient) {
        mRetrofitBuilder.client(okHttpClient);
    }

    /**
     * 设置无网络时是否缓存
     *
     * @param context
     * @param cache   无网络时true使用缓存的数据
     */
    public void setNoNetworkCache(Context context, boolean cache) {
        if (cache) {
            mOkHttpClientBuilder.addInterceptor(new CacheInterceptor(context));
            mRetrofitBuilder.client(mOkHttpClientBuilder.build()).build();
        }
    }

    public Retrofit getRetrofit() {
        return mRetrofitBuilder.build();
    }

}