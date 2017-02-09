package com.rookie.www.topnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.rookie.www.topnews.R;
import com.rookie.www.topnews.adapter.NewsListAdapter;
import com.rookie.www.topnews.entity.News;
import com.rookie.www.topnews.listener.HttpCallbackListener;
import com.rookie.www.topnews.parse.NewsParse;
import com.rookie.www.topnews.utils.HttpUtils;
import com.rookie.www.topnews.utils.ProgressDialogUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvNews = (ListView) findViewById(R.id.lvMain);
        ProgressDialogUtil.showProgressDialog(MainActivity.this);
        HttpUtils.sendHttpRequest("http://v.juhe.cn/toutiao/index?type=&key=0e2e3d9e573eed9381e672a2ff752cca", new HttpCallbackListener() {
            @Override
            public void onFinish(final String respones) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<News> newses = NewsParse.getNews(respones);
                        NewsListAdapter adapter = new NewsListAdapter(newses, R.layout.listview_item_news, lvNews);
                        lvNews.setAdapter(adapter);
                        ProgressDialogUtil.closeProgressDialog();
                    }
                });

            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ProgressDialogUtil.closeProgressDialog();
                        Toast.makeText(MainActivity.this, "加载失败，请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void doClick(View view) {
        switch (view.getId()) {

        }
    }
}
