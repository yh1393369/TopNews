package com.rookie.www.topnews;

import android.app.Application;
import android.content.Context;

/**
 * Created by Hi on 2017/2/9.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
