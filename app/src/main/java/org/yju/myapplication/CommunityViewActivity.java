package org.yju.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.BoardInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CommunityViewActivity extends AppCompatActivity {

    private TextView tv_b_title, tv_b_content;
    DataService dataService = new DataService();
    private String title, content;
    BoardInfo boardInfo = new BoardInfo();
    String b_dtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_view);

        tv_b_title = findViewById(R.id.tv_b_title);
        tv_b_content = findViewById(R.id.tv_b_content);

        Intent intent = getIntent();
        b_dtt = intent.getExtras().getString("b_dtt");
        Log.i("TAG", "onCreate: 게시글 볼수 있냐?" + b_dtt);

        boardInfo.setB_dtt(b_dtt);

        dataService.select.gBoard(boardInfo).enqueue(new Callback<ArrayList<Board>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {
//                ArrayList<Board> boards = response.body();
//                for (int i = 0; i < boards.size(); i++) {
//                    addContentView(boards.get(i).getB_title(), boards.get(i).getB_content());
//                }
                title = response.body().get(0).getB_title();
                content = response.body().get(0).getB_content();
                tv_b_title.setText(title);
                tv_b_content.setText(content);

            }

            @Override
            public void onFailure(Call<ArrayList<Board>> call, Throwable t) {

            }
        });


    }

}