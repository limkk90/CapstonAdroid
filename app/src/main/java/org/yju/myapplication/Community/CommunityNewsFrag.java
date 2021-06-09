package org.yju.myapplication.Community;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityNewsFrag extends Fragment {
    Intent intent;
    private View view;
    RecyclerView recyclerView = null;
    NewsAdapter adapter = null;
    ArrayList<News> bList = new ArrayList<News>();
    LinearLayoutManager linearLayoutManager = null;
    DataService dataService = new DataService();
    String u_id;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.community_news, container, false);

        Bundle bundle3 = getArguments();
        Log.i("TAG", "onCreateView: 뉴수 쪽으로 아이디 넘어옴??" + bundle3);

        recyclerView = view.findViewById(R.id.recyclerView);
        Log.i("notice", "onCreateView: " + bList);
        adapter = new NewsAdapter(bList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        bList.clear();
        getListBoard();
        adapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String title, String pubDate) {
        News item = new News();
        item.setTitle(title);
        item.setPubDate(pubDate);

        bList.add(item);
        adapter.notifyDataSetChanged();
    }

    public void getListBoard() {
        //dataService api 호출
        dataService.boardApi.news().enqueue(new Callback<JsonArray>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

//                ObjectMapper mapper = new ObjectMapper();
//                Map result = mapper.convertValue(response.body(), Map.class);
                Log.i("TAG", "onResponse: result값 확인??" + response.body());

                JsonArray body = response.body();
                int size = body.size();
                for (int i = 0; i < size; i++) {
                    JsonObject jsonObject = (JsonObject) body.get(i);
                    String title = jsonObject.get("title").toString();
                    String link = jsonObject.get("link").toString();
                    String description = jsonObject.get("description").toString();
                    String pubDate = jsonObject.get("pubDate").toString();
                    Log.i("getListNews", "onResponse: "+ title + "pubDate:"+ pubDate);

                    addItem(title, pubDate);

                }
                addItem(null,null);

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
