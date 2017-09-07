package com.example.yunnews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yunnews.adapter.NewsAdapter;
import com.example.yunnews.bean.New;
import com.example.yunnews.util.HttpUtil;
import com.example.yunnews.util.Utilty;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final int UPDATE=1;
    NewsAdapter adapter;
    public List<New> newsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendOkHttpRequest();

    }
    public void sendOkHttpRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=beba7be7334e27c0").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSON(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler handler=new Handler() {
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE:

                    adapter=new NewsAdapter(MainActivity.this,
                            R.layout.listview_item,newsList);
                    ListView listView= (ListView) findViewById(R.id.list_view);
                    listView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };
    public  void parseJSON(String jsonData){

        try{
            JSONObject json = new JSONObject( jsonData);
            JSONObject jsonObject=json.getJSONObject("result");
            JSONArray jsonArray=jsonObject.getJSONArray("list");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=jsonArray.getJSONObject(i);
                String title = obj.getString("title");
                String content = obj.getString("content");
                String pic=obj.getString("pic");
                newsList.add(new New(title,content,pic));

            }
            Message message=new Message();
            message.what=UPDATE;
            handler.sendMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
