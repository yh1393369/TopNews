package com.rookie.www.topnews.parse;

import com.rookie.www.topnews.entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hi on 2017/2/9.
 */

public class NewsParse {

    public static ArrayList<News> newses;

    public static ArrayList<News> getNews(String json){
        if(newses == null){
            newses = new ArrayList<News>();
        }
        newses.clear();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject resultJsonObject = jsonObject.getJSONObject("result");
            String stat = resultJsonObject.getString("stat");
            if(stat.equals("1")){
                JSONArray datas = resultJsonObject.getJSONArray("data");
                for(int i = 0; i < datas.length(); i++){
                    JSONObject singleJsonObject = datas.getJSONObject(i);
                    String title = singleJsonObject.getString("title");
                    String date = singleJsonObject.getString("date");
                    String author = singleJsonObject.getString("author_name");
                    String url = singleJsonObject.getString("url");
                    String imageUrl = singleJsonObject.getString("thumbnail_pic_s");
                    News news = new News(title, date, author, url, imageUrl);
                    newses.add(news);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return newses;
        }
    }

}
