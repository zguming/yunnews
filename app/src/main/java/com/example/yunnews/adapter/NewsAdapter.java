package com.example.yunnews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yunnews.R;
import com.example.yunnews.bean.New;


import java.util.List;



/**
 * Created by Administrator on 2017/8/9.
 */

public class NewsAdapter extends ArrayAdapter<New> {
    private int resourceId;
    public NewsAdapter(Context context,int textViewResourceId,List<New> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        New news=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.title=view.findViewById(R.id.news_title);
            viewHolder.content=view.findViewById(R.id.news_content);
            viewHolder.pic=view.findViewById(R.id.news_pic);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(news.getTitle());
        viewHolder.content.setText(news.getContent());
        Glide.with(getContext()).load(news.getPicurl()).into(viewHolder.pic);
        return view;
    }
    class ViewHolder{
        TextView title;
        TextView content;
        ImageView pic;
    }
}