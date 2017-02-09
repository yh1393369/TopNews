package com.rookie.www.topnews.listener;

/**
 * Created by Hi on 2017/2/9.
 */

public interface HttpCallbackListener {

    void onFinish(String respones);

    void onError(Exception e);

}
