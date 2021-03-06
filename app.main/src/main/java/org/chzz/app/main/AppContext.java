package org.chzz.app.main;

import android.app.Application;
import android.util.Log;


import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.chzz.app.main.engine.CookiesInterceptor;
import org.chzz.app.main.engine.Engine;
import org.chzz.app.main.utlis.ConstantValues;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2016/2/17.
 */
public class AppContext extends Application {
    private static AppContext sInstance;
    private Engine mEngine;

    public AppContext() {
        Log.d("main application", "AppContext()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //    x.Ext.init(this);
        sInstance = this;
        Stetho.initializeWithDefaults(this);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CookiesInterceptor(this.getApplicationContext()))
                .addNetworkInterceptor(new StethoInterceptor())
                .followRedirects(false)
                .build();
        mEngine = new Retrofit.Builder()
                .baseUrl(ConstantValues.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static AppContext getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }




}
