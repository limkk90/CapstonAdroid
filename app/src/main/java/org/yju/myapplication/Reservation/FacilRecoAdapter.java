package org.yju.myapplication.Reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.R;
import org.yju.myapplication.data.Poi;

import java.util.ArrayList;

public class FacilRecoAdapter extends RecyclerView.Adapter<FacilRecoAdapter.ViewHolder> {
    private ArrayList<Poi> mData = null;
    FacilRecoAdapter(ArrayList<Poi> list){
        this.mData = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name, txt_address;

        ViewHolder(View view){
            super(view);
            txt_name = view.findViewById(R.id.txt_facil_name);
            txt_address = view.findViewById(R.id.txt_facil_addres);

        }
    }

    @NonNull
    @Override
    public FacilRecoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_facil, parent, false);
        FacilRecoAdapter.ViewHolder vh = new FacilRecoAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poi item = mData.get(position);
        holder.txt_name.setText(item.getPoiName());
        holder.txt_address.setText(item.getPoiAddress());

    }

    @Override
    public int getItemCount() {
        return (mData != null ? mData.size() : 0);
    }



}
