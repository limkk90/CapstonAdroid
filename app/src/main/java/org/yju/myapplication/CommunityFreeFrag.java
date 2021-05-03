package org.yju.myapplication;

import android.content.Context;
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

import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CommunityFreeFrag extends Fragment {
    Intent intent;
    private View view;
    RecyclerView recyclerView = null;
    CommunityAdapter adapter = null;
    ArrayList<Board> bList = new ArrayList<Board>();
    LinearLayoutManager linearLayoutManager = null;
    DataService dataService = new DataService();
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community_free, container, false);

        Bundle bundle = getArguments();
        Log.i("TAG", "onCreateView: 마지막값" + bundle);

        //글쓰기 화면으로 넘어가는 FloationAction버튼 클릭 했을 때
        floatingActionButton = view.findViewById(R.id.float_btn_freeWrite);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_id = bundle.getString("u_id");
                Log.i("TAG", "onClick: 마지막 번들값 재확인" + u_id);
                assert bundle != null;
                try {
                    if (u_id != null) {
                        intent = new Intent(getActivity(), CommunityInsertAcitivty.class);
                        Log.i("u_id = ", u_id);
                        intent.putExtra("u_id", u_id);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "로그인을 해주세요", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                }
            }
        });
        //===================================================
        //리사이클러뷰 처리
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
                adapter.getItem(pos).getB_dtt().toString();
                Log.e("aaa", adapter.getItem(pos).getB_dtt().toString());
            }
        });

        adapter.notifyDataSetChanged();
        //=============================================

        //dataService api 호출
        dataService.select.FreeBoard().enqueue(new Callback<ArrayList<Board>>() {
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {
                ArrayList<Board> body = response.body();
                Log.i("TAG", "onResponse: 성공" + body);
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
        //================================================

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String title, String content, String writer, String b_dtt){
        Board item = new Board();

        item.setB_title(title);
        item.setB_content(content);
        item.setU_id(writer);
        item.setB_dtt(b_dtt);
        bList.add(item);
        adapter.notifyDataSetChanged();

        // 리사이클러뷰 어뎁터로 값 넘겨주고, 새로고침 시켜줘야됨.
    }
}
