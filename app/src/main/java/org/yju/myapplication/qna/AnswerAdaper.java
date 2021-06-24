package org.yju.myapplication.qna;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.Community.BoardApi;
import org.yju.myapplication.Community.ReplyAdapter;
import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Answer;
import org.yju.myapplication.data.Reply;

import java.util.ArrayList;



public class AnswerAdaper extends RecyclerView.Adapter<AnswerAdaper.ViewHolder> {
    DataService dataService = new DataService();
    private ArrayList<Answer> data = null;
    Answer answer = new Answer();

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private AnswerAdaper.OnItemClickListener listener = null;

    public void setOnItemClickListener(AnswerAdaper.OnItemClickListener listener) {
        this.listener = listener;
    }

    AnswerAdaper(ArrayList<Answer> list) {
        data = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rep_writer, rep_content, rep_regDate, rep_modify, rep_delete;

        ViewHolder(View itemView) {
            super(itemView);

            rep_writer = itemView.findViewById(R.id.answer_writer);
            rep_content = itemView.findViewById(R.id.answer_content);
            rep_regDate = itemView.findViewById(R.id.answer_regDate);
            rep_modify = itemView.findViewById(R.id.answer_modify);
            rep_delete = itemView.findViewById(R.id.answer_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Integer posStr = Integer.valueOf(pos);
                    if (pos != RecyclerView.NO_POSITION) {
                        if (listener != null) {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.answer_item, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdaper.ViewHolder holder, int position) {
        Answer item = data.get(position);
//        Log.i("ReplyAdapter", "onClick: " + item);


        holder.rep_writer.setText(item.getAns_writer());
        holder.rep_content.setText(item.getAns_content());
        holder.rep_regDate.setText(item.getAns_dt());
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

//        holder.rep_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteThisView(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Answer getItem(int position) {
        return data.get(position);
    }

//    public void deleteThisView(int position) {
//        answer = data.get(position);
//
//        Log.i("ReplyAdapter", "포지션???: " + answer);
//        Log.i("ReplyAdapter1", "onResponse: " + answer.getAns_dt());
//        dataService.boardApi.replyDelete(answer).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Log.i("ReplyAdapter", "댓글삭제성공: ");
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });
//    }
}
