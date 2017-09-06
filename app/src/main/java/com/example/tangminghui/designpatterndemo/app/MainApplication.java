package com.example.tangminghui.designpatterndemo.app;

import android.app.Application;
import android.content.Context;

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
    }

    public static MainApplication getInstance() {
        return mInstance;
    }
}
