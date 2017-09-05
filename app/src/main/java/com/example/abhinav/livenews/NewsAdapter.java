package com.example.abhinav.livenews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>
{

   private Context context;
    ArrayList<DataProvider> arrayList=new ArrayList<>();

    public NewsAdapter(Context context, ArrayList<DataProvider> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
        Log.i("con", String.valueOf(arrayList.size()));
    }


    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        NewsHolder newsHolder=new NewsHolder(view);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position)
    {
        holder.textView.setText(arrayList.get(position).getTitle());
        new loadImage(arrayList.get(position).getPosterUrl(),holder.imageView).execute();
    }



    @Override
    public int getItemCount() {
        Log.i("Adapter", String.valueOf(arrayList.size()));
        return arrayList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener
    {
        ImageView imageView;
        TextView textView;

        public NewsHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image);
            textView=(TextView)itemView.findViewById(R.id.headlines);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context,"fuck off",Toast.LENGTH_LONG).show();

                    final Intent intent;
                    int i=getAdapterPosition();
                    DataProvider dataProvider=arrayList.get(i);
                    Toast.makeText(context,dataProvider.getTitle(),Toast.LENGTH_LONG).show();
                    String description = dataProvider.getDescription();
                    String title = dataProvider.getTitle();
                    Log.i("clicked",title);
                    String path = dataProvider.getPosterUrl();
                    String urlNews = dataProvider.getUrlNews();
                    intent=new Intent(context,NewsDescription.class);
                    intent.putExtra("description",description);
                    intent.putExtra("title",title);
                    intent.putExtra("path",path);
                    intent.putExtra("url",urlNews);

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
    public class loadImage extends AsyncTask<Void,Void,Bitmap>
    {
        String url;
        ImageView imageView;

        loadImage(String url,ImageView imageView)
        {
            this.url=url;
            this.imageView=imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            URL url1 = null;
            try {
                url1 = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmap;
            } catch (Exception e) {
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
