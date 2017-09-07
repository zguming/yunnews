package com.example.yunnews.bean;


/**
 * Created by Administrator on 2017/8/10.
 */

public class New {
    private String title;
    private String content;
    private String picurl;
    public New(String title,String content,String pic) {
        this.title=title;
        this.content=content;
        this.picurl=pic;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getPicurl() {
        return picurl;
    }
}

