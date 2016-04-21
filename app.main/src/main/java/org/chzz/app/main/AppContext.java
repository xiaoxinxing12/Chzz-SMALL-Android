package org.chzz.app.main;

import android.app.Application;
import android.util.Log;


/**
 * Created by Administrator on 2016/2/17.
 */
public class AppContext extends Application {
    private static AppContext sInstance;

    public AppContext() {
        Log.d("main application", "AppContext()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //    x.Ext.init(this);
        sInstance = this;

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static AppContext getInstance() {
        return sInstance;
    }

}
