package org.yju.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.data.Board;

import java.util.ArrayList;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private ArrayList<Board> mData = null;

    CommunityAdapter(ArrayList<Board> list){
        mData = list;
        Log.i("왜", mData.toString());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, content, date, writer;

        ViewHolder(View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.title_list);
            content = itemView.findViewById(R.id.content_list);
            date = itemView.findViewById(R.id.date_list);
            writer = itemView.findViewById(R.id.writer_list);
        }
    }

    @NonNull
    @Override
    public CommunityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.community_item, parent, false);
        CommunityAdapter.ViewHolder vh = new CommunityAdapter.ViewHolder(view);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Board item = mData.get(position);

        holder.title.setText(item.getB_title());
        holder.content.setText(item.getB_content());
        holder.writer.setText(item.getU_id());
//        holder.date.setText(item.getB_dtt().toString());

        holder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.content.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.writer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//        holder..setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.title.setGravity(Gravity.CENTER);
        holder.content.setGravity(Gravity.CENTER);
        holder.writer.setGravity(Gravity.CENTER);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
