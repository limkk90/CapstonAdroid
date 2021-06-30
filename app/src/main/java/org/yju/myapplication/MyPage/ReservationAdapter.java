package org.yju.myapplication.MyPage;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.R;
import org.yju.myapplication.data.Charger;
import org.yju.myapplication.data.Rsvt;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    private ArrayList<Rsvt> data = null;

    public interface OnItemCLickListener{
        void onItemClick(View v, int pos);
    }

    private OnItemCLickListener listener = null;

    public void setOnItemClickListener(OnItemCLickListener listener){
        this.listener = listener;
    }

    ReservationAdapter(ArrayList<Rsvt> list){
        data = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rsvt_record_stationId;
        TextView rsvt_record_chargerId;
        TextView rsvt_record_startTime;
        TextView rsvt_record_endTime;


        ViewHolder(View itemView){
            super(itemView);
//            evImg = itemView.findViewById(R.id.charger_img);
//            chargeruse = itemView.findViewById(R.id.charger_use);
            rsvt_record_stationId = itemView.findViewById(R.id.rsvt_stat_id);
            rsvt_record_chargerId = itemView.findViewById(R.id.rsvt_charger_id);
            rsvt_record_startTime = itemView.findViewById(R.id.rsvt_starttime);
            rsvt_record_endTime = itemView.findViewById(R.id.rsvt_endtime);
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
        View view = inflater.inflate(R.layout.rsvtrecoritem, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rsvt item = data.get(position);

//        holder.evImg.setImageResource(R.drawable.ic_baseline_ev_station_24);
//        holder..setText(item.get());
        holder.rsvt_record_stationId.setText(item.getStat_id());
        holder.rsvt_record_chargerId.setText(item.getChg_id());
        holder.rsvt_record_startTime.setText(item.getRsvt_start());
        holder.rsvt_record_endTime.setText(item.getRsvt_end());
//
//        holder.chargeruse.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//
//        holder.evImg.setForegroundGravity(Gravity.CENTER);
//        holder.chargeruse.setGravity(Gravity.CENTER);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Rsvt getItem(int position) {
        return data.get(position);
    }
}
