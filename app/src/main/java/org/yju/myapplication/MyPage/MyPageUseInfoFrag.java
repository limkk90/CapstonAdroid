package org.yju.myapplication.MyPage;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.yju.myapplication.Community.CommunityAdapter;
import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.Rsvt;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageUseInfoFrag extends Fragment {
    View view;

    RecyclerView board_record, rsvt_record = null;
    CommunityAdapter cAdapter = null;
    ReservationAdapter rAdapter = null;

    ArrayList<Board> bList = new ArrayList<Board>();
    LinearLayoutManager linearLayoutManager, linearLayoutManager2 = null;
    DataService dataService = new DataService();
    ArrayList<Rsvt> rList = new ArrayList<Rsvt>();
    String u_id;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mypage_useinfo, container, false);


        Bundle bundle2 = getArguments();
        u_id = bundle2.getString("u_id").replace("[", "").replace("{", "").replace("u_id=", "");
        Log.i("TAG", "onCreateView: u_id?" + u_id);
        // board RecyclerView
        board_record = view.findViewById(R.id.board_record);

        cAdapter = new CommunityAdapter(bList);
        board_record.setAdapter(cAdapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        board_record.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(board_record.getContext(), linearLayoutManager.getOrientation());
        board_record.addItemDecoration(dividerItemDecoration);

        // rsvt RecyclerView
        rsvt_record = view.findViewById(R.id.rsvt_record);
        rAdapter = new ReservationAdapter(rList);
        rsvt_record.setAdapter(rAdapter);
        linearLayoutManager2 = new LinearLayoutManager(getActivity());
        rsvt_record.setLayoutManager(linearLayoutManager2);

        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(rsvt_record.getContext(), linearLayoutManager.getOrientation());
        rsvt_record.addItemDecoration(dividerItemDecoration1);


        return view;


    }

    @Override
    public void onStart() {
        super.onStart();
        bList.clear();
        rList.clear();
        getListBoard();
        getRsvt();
        cAdapter.notifyDataSetChanged();
        rAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String title, String content, String writer, String b_no) {
        Board item = new Board();
        item.setB_title(title);
        item.setB_content(content);
        item.setU_id(writer);
        item.setB_no(b_no);
        bList.add(item);
        cAdapter.notifyDataSetChanged();

    }

    public void addItem2(String chg_id, String stat_id, String rsvt_start, String rsvt_end) {
        Rsvt rsvt = new Rsvt();
        rsvt.setChg_id(chg_id);
        rsvt.setStat_id(stat_id);
        rsvt.setRsvt_start(rsvt_start);
        rsvt.setRsvt_end(rsvt_end);
        rList.add(rsvt);
        rAdapter.notifyDataSetChanged();
    }

    public void getListBoard(){
        dataService.myPageApi.myBoard(u_id).enqueue(new Callback<ArrayList<Board>>() {
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {
                ArrayList<Board> body = response.body();
                Log.i("TAG", "onResponse: board123123" + body);
                for (int i = 0; i < body.size(); i++) {
                    addItem(body.get(i).getB_title(), body.get(i).getB_content(), body.get(i).getU_id(), body.get(i).getB_no());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Board>> call, Throwable t) {

            }
        });
    }

    public void getRsvt(){
        dataService.myPageApi.myRsvt(u_id).enqueue(new Callback<ArrayList<Rsvt>>() {
            @Override
            public void onResponse(Call<ArrayList<Rsvt>> call, Response<ArrayList<Rsvt>> response) {
                ArrayList<Rsvt> body = response.body();
                Log.i("TAG", "onResponse: reservation" + body);
                for (int i=0; i<body.size(); i++) {
                    addItem2(body.get(i).getChg_id(), body.get(i).getStat_id(), body.get(i).getRsvt_start(), body.get(i).getRsvt_end());
                }
                addItem(null, null, null, null);
            }

            @Override
            public void onFailure(Call<ArrayList<Rsvt>> call, Throwable t) {

            }
        });
    }
}
