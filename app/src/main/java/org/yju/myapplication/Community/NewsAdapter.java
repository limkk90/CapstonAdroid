package org.yju.myapplication.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.R;
import org.yju.myapplication.data.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<News> nData = null;

    public NewsAdapter(ArrayList<News> nList) {
        this.nData = nList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.news_item, parent, false);
        NewsAdapter.ViewHolder vh = new NewsAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News item = nData.get(position);

        holder.title.setText(item.getTitle());
        holder.regDate.setText(item.getPubDate());
    }

    @Override
    public int getItemCount() {
        return nData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, description,regDate;
        ViewHolder(View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.txt_newsTitle);
            regDate = itemView.findViewById(R.id.txt_newsRegDate);



        }
    }
}
