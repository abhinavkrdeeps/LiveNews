package com.example.abhinav.livenews;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  //  Button b1,b2,b3,b4,b5,b6;
    String apiKey="63293719f91c47d9bde648740565c1a9";
    String urlPath;
    ArrayList<DataProvider> arrayList=new ArrayList<>();
    DisplayNews displayNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // urlPath=" https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=63293719f91c47d9bde648740565c1a9";


    }
    public void onClick(View view)
    {

        int id=view.getId();
       switch (id)
       {
           case R.id.buisness:

               Intent intent= new Intent(MainActivity.this,DisplayNews.class);
               urlPath="https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=63293719f91c47d9bde648740565c1a9";
               intent.putExtra("India",urlPath);
               startActivity(intent);
               break;
           case R.id.indian:
                intent= new Intent(MainActivity.this,DisplayNews.class);
                 urlPath=" https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=63293719f91c47d9bde648740565c1a9";
               intent.putExtra("India",urlPath);
               startActivity(intent);
               break;
           case R.id.world:
               intent= new Intent(MainActivity.this,DisplayNews.class);
               urlPath=" https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=63293719f91c47d9bde648740565c1a9";

               intent.putExtra("India",urlPath);
               startActivity(intent);

               break;
           case R.id.cricket:
               intent= new Intent(MainActivity.this,DisplayNews.class);
               urlPath=" https://newsapi.org/v1/articles?source=espn-cric-info&sortBy=latest&apiKey=63293719f91c47d9bde648740565c1a9";

               intent.putExtra("India",urlPath);
               startActivity(intent);

               break;
           case R.id.sport:
               intent= new Intent(MainActivity.this,DisplayNews.class);
               urlPath=" https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=63293719f91c47d9bde648740565c1a9";

               intent.putExtra("India",urlPath);
               startActivity(intent);

               break;
           case R.id.political:
               intent= new Intent(MainActivity.this,DisplayNews.class);
               urlPath="https://newsapi.org/v1/articles?source=entertainment-weekly&sortBy=top&apiKey=63293719f91c47d9bde648740565c1a9";

               intent.putExtra("India",urlPath);
               startActivity(intent);

               break;


       }

    }

    }


