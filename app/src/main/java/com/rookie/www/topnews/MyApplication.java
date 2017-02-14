package com.rookie.www.topnews;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hi on 2017/2/9.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> activities;

    public MyApplication(){
        activities = new ArrayList<Activity>();
    }

    public static MyApplication getInstance(){
        if(instance == null){
            instance = new MyApplication();
        }
        return instance;
    }

    public void addActivity(Activity activity){
        if(activities != null && activities.size() > 0){
            if(!activities.contains(activity)){
                activities.add(activity);
            }
        }else {
            activities.add(activity);
        }
    }

    public void exit(){
        if(activities != null && activities.size() > 0){
            for (Activity activity : activities){
                activity.finish();
            }
        }
        System.exit(0);
    }

}
