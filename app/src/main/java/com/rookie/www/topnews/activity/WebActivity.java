package com.rookie.www.topnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.rookie.www.topnews.MyApplication;
import com.rookie.www.topnews.R;

public class WebActivity extends AppCompatActivity {

    private WebView wvWeb;
    private ProgressBar pbLoadWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        MyApplication.getInstance().addActivity(this);
        wvWeb = (WebView) findViewById(R.id.wvWeb);
        pbLoadWeb = (ProgressBar) findViewById(R.id.pbLoadWeb);
        WebSettings settings = wvWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        wvWeb.setWebViewClient(new WebViewClient());
        wvWeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100){
                    pbLoadWeb.setVisibility(View.GONE);
                }else {
                    pbLoadWeb.setVisibility(View.VISIBLE);
                    pbLoadWeb.setProgress(newProgress);
                }
            }
        });
        String url = getIntent().getStringExtra("url");
        if(url != null){
            wvWeb.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        if(wvWeb.canGoBack()){
            wvWeb.goBack();
        }else {
            finish();
        }
    }
}
