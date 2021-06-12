package org.yju.myapplication.Reservation;

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

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    private ArrayList<Charger> data = null;

    public interface OnItemCLickListener{
        void onItemClick(View v, int pos);
    }

    private ReservationAdapter.OnItemCLickListener listener = null;

    public void setOnItemClickListener(ReservationAdapter.OnItemCLickListener listener){
        this.listener = listener;
    }

    ReservationAdapter(ArrayList<Charger> list){
        data = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView evImg;
        TextView chargeruse;

        ViewHolder(View itemView){
            super(itemView);
            evImg = itemView.findViewById(R.id.charger_img);
            chargeruse = itemView.findViewById(R.id.charger_use);

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
    public ReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.charger_item, parent, false);
        ReservationAdapter.ViewHolder vh = new ReservationAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Charger item = data.get(position);

        holder.evImg.setImageResource(R.drawable.ic_baseline_ev_station_24);
        holder.chargeruse.setText(item.getChg_rsvt());

        holder.chargeruse.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        holder.evImg.setForegroundGravity(Gravity.CENTER);
        holder.chargeruse.setGravity(Gravity.CENTER);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Charger getItem(int position) {
        return data.get(position);
    }
}
