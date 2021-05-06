package org.yju.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.Criteria;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CommunityTipFrag  extends Fragment {
    Intent intent;
    private View view;
    RecyclerView recyclerView = null;
    CommunityAdapter adapter = null;
    ArrayList<Board> bList = new ArrayList<Board>();
    LinearLayoutManager linearLayoutManager = null;
    DataService dataService = new DataService();
    //    Board board = new Board();
//    Criteria criteria = new Criteria();
//    private char cat_cd = '2';

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community_tip, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        adapter = new CommunityAdapter(bList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter.setOnItemCLickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(View v, int pos) {
                Integer a = Integer.valueOf(pos);
                Log.e("asd", a.toString());
                adapter.getItem(pos).getB_dtt();
                Log.e("aaa", adapter.getItem(pos).getB_dtt());
                intent = new Intent(getContext(), CommunityViewActivity.class);
                intent.putExtra("b_dtt", adapter.getItem(pos).getB_dtt());
                startActivity(intent);

            }
        });


        adapter.notifyDataSetChanged();



        dataService.select.TipBoard().enqueue(new Callback<ArrayList<Board>>() {
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {
                ArrayList<Board> body = response.body();
                Log.i("TAG", "onResponse: 성공!!" + response.body());
                for (int i = 0; i < body.size(); i++) {
                    addItem(body.get(i).getB_title(), body.get(i).getB_content(), body.get(i).getU_id(), body.get(i).getB_dtt());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Board>> call, Throwable t) {
                t.printStackTrace();

            }
        });


        return view;


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String title, String content, String writer, String b_dtt) {
        Board item = new Board();
        item.setB_title(title);
        item.setB_content(content);
        item.setU_id(writer);
        item.setB_dtt(b_dtt);
        bList.add(item);
        adapter.notifyDataSetChanged();

    }
}
