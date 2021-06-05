package org.yju.myapplication.Community;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.R;
import org.yju.myapplication.data.Reply;

import java.util.ArrayList;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ViewHolder>{
    private ArrayList<Reply> data = null;

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    ReplyAdapter(ArrayList<Reply> list){
        data = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rep_writer, rep_content, rep_regDate, rep_modify, rep_delete;

        ViewHolder(View itemView){
            super(itemView);

            rep_writer = itemView.findViewById(R.id.reply_writer);
            rep_content = itemView.findViewById(R.id.reply_content);
            rep_regDate = itemView.findViewById(R.id.reply_regDate);
            rep_modify = itemView.findViewById(R.id.reply_modify);
            rep_delete = itemView.findViewById(R.id.reply_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Integer posStr = Integer.valueOf(pos);
                    if(pos != RecyclerView.NO_POSITION){
                        if(listener != null){
                            listener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.reply_item, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reply item = data.get(position);

        holder.rep_writer.setText(item.getR_writer());
        holder.rep_content.setText(item.getR_content());
        holder.rep_regDate.setText(item.getR_dtt());
        holder.rep_modify.setText("수정");
        holder.rep_delete.setText("삭제");

        holder.rep_writer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.rep_content.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.rep_regDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.rep_modify.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.rep_delete.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        holder.rep_writer.setGravity(Gravity.CENTER);
        holder.rep_content.setGravity(Gravity.CENTER);
        holder.rep_regDate.setGravity(Gravity.CENTER);
        holder.rep_modify.setGravity(Gravity.CENTER);
        holder.rep_delete.setGravity(Gravity.CENTER);

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public Reply getItem(int position){
        return  data.get(position);
    }
}
