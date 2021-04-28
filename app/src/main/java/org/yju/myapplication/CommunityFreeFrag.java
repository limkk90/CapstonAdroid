package org.yju.myapplication;

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

import java.time.LocalDateTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CommunityFreeFrag extends Fragment {
    private View view;
    RecyclerView recyclerView = null;
    CommunityAdapter adapter = null;
    ArrayList<Board> bList = new ArrayList<Board>();
    LinearLayoutManager linearLayoutManager = null;
    DataService dataService = new DataService();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community_free, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        adapter = new CommunityAdapter(bList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter.notifyDataSetChanged();



        dataService.select.ListCall().enqueue(new Callback<ArrayList<Board>>() {
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {
                ArrayList<Board> body = response.body();
                Log.i("TAG", "onResponse: 성공" + body);
                for (int i = 0; i < body.size(); i++) {
                    addItem(body.get(i).getB_title(), body.get(i).getB_content(), body.get(i).getU_id());
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
    public void addItem(String title, String content, String writer){
        Board item = new Board();
        item.setB_title(title);
        item.setB_content(content);
        item.setU_id(writer);
        bList.add(item);
        adapter.notifyDataSetChanged();

        // 리사이클러뷰 어뎁터로 값 넘겨주고, 새로고침 시켜줘야됨.
    }
}
