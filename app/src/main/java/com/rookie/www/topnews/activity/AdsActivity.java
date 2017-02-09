package com.rookie.www.topnews.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rookie.www.topnews.R;

public class AdsActivity extends AppCompatActivity {

    private TextView tvAdsTime;

    private int time = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        tvAdsTime = (TextView) findViewById(R.id.tvAdsTime);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (time > 0){
                    try {
                        Thread.sleep(1000);
                        time--;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvAdsTime.setText(time + "s");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AdsActivity.this, MainActivity.class);
                        startActivity(intent);
                        AdsActivity.this.finish();
                    }
                });
            }
        }).start();
    }
}
