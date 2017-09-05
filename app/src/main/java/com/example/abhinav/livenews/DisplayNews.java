package com.example.abhinav.livenews;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DisplayNews extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    MainActivity mainActivity;
    NewsAdapter newsAdapter;
    FloatingActionButton floatingActionButton;
    ArrayList<DataProvider> arrayList;
     String urlPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayNews.this,MainActivity.class));
            }
        });
        Bundle bundle=getIntent().getExtras();
        loadData(bundle.getString("India"));
        arrayList=new ArrayList<>();
        newsAdapter = new NewsAdapter(this,arrayList);
        newsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void loadData(String url1)
    {
        AsyncTask<String,Void,String> task=new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                URL url = null;
                try {
                    url = new URL(params[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    Log.i("Code", urlConnection.getResponseMessage());
                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String info = bufferedReader.readLine();
                    JSONObject jsonObject = new JSONObject(info);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    for (int p = 0; p < jsonArray.length(); p++) {
                        JSONObject object = jsonArray.getJSONObject(p);
                        DataProvider dataProvider = new DataProvider();
                        dataProvider.setTitle(object.getString("title"));
                        Log.i("News ", dataProvider.getTitle());
                        dataProvider.setDescription(object.getString("description"));
                        Log.i("News ", dataProvider.getDescription());
                        dataProvider.setPosterUrl(object.getString("urlToImage"));
                        dataProvider.setUrlNews(object.getString("url"));
                        arrayList.add(dataProvider);
                        NewsAdapter newsAdapter = new NewsAdapter(getApplicationContext(), arrayList);
                        newsAdapter.notifyDataSetChanged();

                    }

                    Log.i("News ", info);
                    inputStream.close();
                    bufferedReader.close();
                    return info;
                } catch (Exception e) {

                }
                return null;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                newsAdapter.notifyDataSetChanged();
            }
        };
            task.execute(url1);


            }
        }

