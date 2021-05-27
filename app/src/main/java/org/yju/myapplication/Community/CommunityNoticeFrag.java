package org.yju.myapplication.Community;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityNoticeFrag extends Fragment {
    Intent intent;
    private View view;
    RecyclerView recyclerView = null;
    CommunityAdapter adapter = null;
    ArrayList<Board> bList = new ArrayList<Board>();
    LinearLayoutManager linearLayoutManager = null;
    DataService dataService = new DataService();
    String u_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community_notice, container, false);

        Bundle bundle2 = getArguments();
        Log.i("TAG", "onCreateView: 공지사항 쪽으로 아이디 넘어옴??" + bundle2);

        recyclerView = view.findViewById(R.id.recyclerView);
        Log.i("notice", "onCreateView: " + bList);
        adapter = new CommunityAdapter(bList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        adapter.setOnItemCLickListener(new CommunityAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemCLick(View v, int pos) {
                u_id = bundle2.getString("u_id");
                Integer a = Integer.valueOf(pos);
                adapter.getItem(pos).getB_no();
                adapter.getItem(pos).getU_id();
                intent = new Intent(getContext(), CommunityViewActivity.class);
                intent.putExtra("b_no", adapter.getItem(pos).getB_no());
                intent.putExtra("u_id", adapter.getItem(pos).getU_id());
                startActivity(intent);
            }
        });
        bList.clear();
        getListBoard();
        adapter.notifyDataSetChanged();
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String title, String content, String writer, String b_no) {
        Board item = new Board();
        item.setB_title(title);
        item.setB_content(content);
        item.setU_id(writer);
        item.setB_no(b_no);
        bList.add(item);
        adapter.notifyDataSetChanged();
    }

    public void getListBoard() {
        //dataService api 호출
        dataService.boardApi.BoardNotify().enqueue(new Callback<ArrayList<Board>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {
                ArrayList<Board> body = response.body();
                Log.i("TAG", "onResponse: 성공" + body);
                for (int i = 0; i < body.size(); i++) {
                    addItem(body.get(i).getB_title(), body.get(i).getB_content(), body.get(i).getU_id(), body.get(i).getB_no());
                }
                addItem(null, null, null, null);
            }

            @Override
            public void onFailure(Call<ArrayList<Board>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}