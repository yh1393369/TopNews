package com.rookie.www.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rookie.www.topnews.MyApplication;
import com.rookie.www.topnews.R;
import com.rookie.www.topnews.adapter.NewsListAdapter;
import com.rookie.www.topnews.entity.News;
import com.rookie.www.topnews.listener.HttpCallbackListener;
import com.rookie.www.topnews.parse.NewsParse;
import com.rookie.www.topnews.utils.Constant;
import com.rookie.www.topnews.utils.HttpUtils;
import com.rookie.www.topnews.utils.ProgressDialogUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvNews;
    private ArrayList<News> newses;
    private NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApplication.getInstance().addActivity(this);
        lvNews = (ListView) findViewById(R.id.lvMain);
        loadNews(Constant.NEWS_TYPE_TOP);
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = newses.get(position).getUrl();
                if(url != null){
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadNews(String newsType){
        ProgressDialogUtil.showProgressDialog(MainActivity.this);
        HttpUtils.sendHttpRequest(Constant.getUrl(newsType), new HttpCallbackListener() {
            @Override
            public void onFinish(final String respones) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newses = NewsParse.getNews(respones);
                        if(adapter == null){
                            adapter = new NewsListAdapter(MainActivity.this, newses, R.layout.listview_item_news, lvNews);
                            lvNews.setAdapter(adapter);
                        }else {
                            adapter.notifyDataSetChanged();
                        }
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
        String newsType = "";
        switch (view.getId()) {
            case R.id.tvMainFashion :
                newsType = Constant.NEWS_TYPE_FASHION;
                break;
            case R.id.tvMainSociety :
                newsType = Constant.NEWS_TYPE_SOCIETY;
                break;
            case R.id.tvMainHome :
                newsType = Constant.NEWS_TYPE_HOME;
                break;
            case R.id.tvMainAbroad :
                newsType = Constant.NEWS_TYPE_ABROAD;
                break;
            case R.id.tvMainAmuse :
                newsType = Constant.NEWS_TYPE_AMUSE;
                break;
            case R.id.tvMainSport :
                newsType = Constant.NEWS_TYPE_SPORT;
                break;
            case R.id.tvMainWar :
                newsType = Constant.NEWS_TYPE_WAR;
                break;
            case R.id.tvMainScience :
                newsType = Constant.NEWS_TYPE_SCIENCE;
                break;
        }
        loadNews(newsType);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().exit();
    }
}
