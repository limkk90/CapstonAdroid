package org.yju.myapplication.qna;

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

import org.yju.myapplication.Community.CommunityAdapter;
import org.yju.myapplication.Community.CommunityViewActivity;
import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.Question;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QnaFaqFrag extends Fragment {
    private View view;
    Intent intent;
    RecyclerView recyclerView = null;
    QnaAdapter adapter = null;
    ArrayList<Question> bList = new ArrayList<Question>();
    LinearLayoutManager linearLayoutManager = null;
    DataService dataService = new DataService();
    String u_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.qna_faq, container, false);
        Log.i("TAG", "onCreateView: in");
        Bundle bundle = getArguments();
        Log.i("TAG", "onCreateView: 아이디 넘어옴??" + bundle);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new QnaAdapter(bList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);



        adapter.setOnItemCLickListener(new QnaAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(View v, int pos) {
                u_id = bundle.getString("u_id");
                Integer a = Integer.valueOf(pos);
                adapter.getItem(pos).getQ_dtt();
                adapter.getItem(pos).getU_id();
                intent = new Intent(getContext(), QnaViewActivity.class);
                intent.putExtra("q_dtt", adapter.getItem(pos).getQ_dtt());
                intent.putExtra("u_id", adapter.getItem(pos).getU_id());
                startActivity(intent);
            }
        });
        bList.clear();
        getListBoard();
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String title, String content, String writer, String q_dtt) {
        Question item = new Question();
        item.setQ_title(title);
        item.setQ_content(content);
        item.setU_id(writer);
        item.setQ_dtt(q_dtt);
        bList.add(item);
        adapter.notifyDataSetChanged();
    }

    public void getListBoard() {
        //dataService api 호출
        Log.i("TAG", "getListBoard: in");
        dataService.qnaApi.qList().enqueue(new Callback<ArrayList<Question>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                ArrayList<Question> body = response.body();
                Log.i("TAG", "onResponse: 성공" + body);
                for (int i = 0; i < body.size(); i++) {
                    addItem(body.get(i).getQ_title(), body.get(i).getQ_content(), body.get(i).getU_id(), body.get(i).getQ_dtt());
                }
                addItem(null, null, null, null);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
