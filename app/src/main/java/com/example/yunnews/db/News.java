package com.example.yunnews.db;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/8/9.
 */

public class News extends DataSupport {
    public String title;
    public String content;
    public String pic;
    public String news_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }
}
