package org.yju.myapplication.Reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.R;
import org.yju.myapplication.data.Charger;
import org.yju.myapplication.data.Rsvt;

import java.util.ArrayList;

public class CheckTodayRsvtAdapter extends RecyclerView.Adapter<CheckTodayRsvtAdapter.ViewHolder> {
        private ArrayList<Rsvt> rsvtData = null;
    CheckTodayRsvtAdapter(ArrayList<Rsvt> list){
        rsvtData = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rsvt_start, rsvt_end, rsvt_user_id;

        ViewHolder(View view){
            super(view);
            rsvt_start = view.findViewById(R.id.txt_start);
            rsvt_end = view.findViewById(R.id.txt_end);
            rsvt_user_id = view.findViewById(R.id.txt_rsvtUser);
        }
    }

    @NonNull
    @Override
    public CheckTodayRsvtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.charger_item, parent, false);
        CheckTodayRsvtAdapter.ViewHolder vh = new CheckTodayRsvtAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rsvt rsvt = rsvtData.get(position);


    }

    @Override
    public int getItemCount() {
        return 0;
    }



}