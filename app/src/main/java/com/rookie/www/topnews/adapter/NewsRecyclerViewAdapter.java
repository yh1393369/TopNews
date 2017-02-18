package com.rookie.www.topnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.rookie.www.topnews.R;
import com.rookie.www.topnews.activity.WebActivity;
import com.rookie.www.topnews.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rookie on 2017/2/18.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private Context context;
    private List<News> newses;

    public NewsRecyclerViewAdapter(Context context, List<News> newses){
        if(newses == null){
            newses = new ArrayList<News>();
        }
        this.context = context;
        this.newses = newses;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_item_news, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.display(holder.ivImage, newses.get(position).getImageUrl());
        holder.tvTitle.setText(newses.get(position).getTitle());
        holder.tvAuthor.setText(newses.get(position).getAutor());
        holder.tvDate.setText(newses.get(position).getDate());
        holder.llNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = newses.get(position).getUrl();
                if(url != null){
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("url", url);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llNews;
        ImageView ivImage;
        TextView tvTitle,tvAuthor,tvDate;

        public NewsViewHolder(View itemView) {
            super(itemView);
            llNews = (LinearLayout) itemView.findViewById(R.id.llNews);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        }
    }

}
