package com.example.tangminghui.designpatterndemo.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by hui on 2017/9/6.
 */

public class MainApplication extends Application {

    private Context mContext;
    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;
        initLogger();
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static MainApplication getInstance() {
        return mInstance;
    }
}
