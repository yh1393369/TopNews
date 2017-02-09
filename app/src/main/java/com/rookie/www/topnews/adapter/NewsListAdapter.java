package com.rookie.www.topnews.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rookie.www.topnews.MyApplication;
import com.rookie.www.topnews.R;
import com.rookie.www.topnews.entity.News;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hi on 2017/2/9.
 */

public class NewsListAdapter extends BaseAdapter {

    private ArrayList<News> list;
    private int itemLayoutId;
    private ListView lvNews;

    private Thread workThread;

    private List<ImageLoadTask> tasks = new ArrayList<ImageLoadTask>();

    private static final int HANDLER_LOAD_IMAGE_SUCCESS = 0;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_LOAD_IMAGE_SUCCESS:
                    ImageLoadTask task = (ImageLoadTask) msg.obj;
                    ImageView ivImage = (ImageView) lvNews.findViewWithTag(task.position);
                    if (ivImage != null) {
                        if (task.bitmap != null) {
                            ivImage.setImageBitmap(task.bitmap);
                        } else {
                            ivImage.setImageResource(R.mipmap.ic_launcher);
                        }
                    }
                    break;
            }
        }
    };

    public NewsListAdapter(ArrayList<News> list, int itemLayoutId, ListView lvNews) {
        if (list == null) {
            list = new ArrayList<News>();
        }
        this.list = list;
        this.itemLayoutId = itemLayoutId;
        this.lvNews = lvNews;
        workThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!tasks.isEmpty()) {
                        ImageLoadTask task = tasks.remove(0);
                        Bitmap bitmap = loadBitmap(task.imageUrl);
                        task.bitmap = bitmap;
                        Message message = new Message();
                        message.what = HANDLER_LOAD_IMAGE_SUCCESS;
                        message.obj = task;
                        handler.sendMessage(message);
                    }else {
                        synchronized (workThread){
                            try {
                                workThread.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        workThread.start();
    }

    private Bitmap loadBitmap(String imageUrl) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(imageUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return bitmap;
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(MyApplication.getContext(), itemLayoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        News news = list.get(position);
        String imageUrl = news.getImageUrl();
        String title = news.getTitle();
        String author = news.getAutor();
        String date = news.getDate();
        viewHolder.tvTitle.setText(title);
        viewHolder.tvAuthor.setText(author);
        viewHolder.tvDate.setText(date);
        viewHolder.ivImage.setTag(position);
        ImageLoadTask task = new ImageLoadTask();
        task.imageUrl = imageUrl;
        task.position = position;
        tasks.add(task);
        synchronized (workThread){
            workThread.notify();
        }
        return convertView;
    }

    class ViewHolder {
        ImageView ivImage;
        TextView tvTitle, tvAuthor, tvDate;
    }

    class ImageLoadTask {
        String imageUrl;
        Bitmap bitmap;
        int position;
    }
}
