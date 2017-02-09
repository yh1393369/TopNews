package com.rookie.www.topnews.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.rookie.www.topnews.MyApplication;

/**
 * Created by Hi on 2017/2/9.
 */

public class ProgressDialogUtil {

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    public static void closeProgressDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

}
