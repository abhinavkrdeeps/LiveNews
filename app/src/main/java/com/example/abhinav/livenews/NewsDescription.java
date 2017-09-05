package com.example.abhinav.livenews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsDescription extends AppCompatActivity {
    TextView textView,textView1;
    ImageView imageView;
    String full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_description);
        textView=(TextView)findViewById(R.id.text_title);
        textView1=(TextView)findViewById(R.id.text_description);
        imageView=(ImageView)findViewById(R.id.image_description);
        Bundle bundle=getIntent().getExtras();
        String title=bundle.getString("title");
        String description=bundle.getString("description");
        String path=bundle.getString("path");
        final String url4=bundle.getString("url");
        textView.setText(title);
        //textView1.setText(description);
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url=null;
                try{
                    url=new URL(url4);
                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    InputStream inputStream=urlConnection.getInputStream();
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    full=bufferedReader.readLine();
                    Log.i("full",full);

                }catch(Exception e)
                {

                }

            }
        }).start();
        textView1.setText(description);
        new load(path,imageView).execute();
    }
    class load extends AsyncTask<Void,Void,Bitmap>
    {
        String url;
        ImageView imageView;
        public load(String url,ImageView imageView)
        {
            this.url=url;
            this.imageView=imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            URL url1=null;
            try
            {
                url1=new URL(url);
                HttpURLConnection urlConnection= (HttpURLConnection) url1.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.connect();
                InputStream inputStream=urlConnection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmap;
            }
            catch(Exception e)
            {
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
        }
    }

