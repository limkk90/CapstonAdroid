package org.yju.myapplication.Community;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.BoardInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CommunityViewActivity extends AppCompatActivity {

    private TextView tv_b_title, tv_b_content, tv_view_update, tv_view_delete;
    DataService dataService = new DataService();
    private String title, content;
    BoardInfo boardInfo = new BoardInfo();
    String b_dtt, b_u_id, u_id;
    Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_view);

        tv_b_title = findViewById(R.id.tv_b_title);
        tv_b_content = findViewById(R.id.tv_b_content);
        tv_view_update = findViewById(R.id.tv_view_update);
        tv_view_delete = findViewById(R.id.tv_view_delete);

        final Intent[] intent = {getIntent()};
        b_dtt = intent[0].getExtras().getString("b_dtt");
        b_u_id = intent[0].getExtras().getString("b_u_id");
        Log.i("TAG", "onCreate: 게시글 볼수 있냐?" + b_dtt);
        Log.i("TAG", "onCreate: 유저아이디 넘어오냐?" + b_u_id);

        boardInfo.setB_dtt(b_dtt);

        dataService.boardApi.gBoard(boardInfo).enqueue(new Callback<List<Object>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                title = (String)((Map) response.body().get(0)).get("b_title");
                content = (String)((Map) response.body().get(0)).get("b_content");
                tv_b_title.setText(title);
                tv_b_content.setText(content);

            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }
        });

        // 삭제
        Intent intent1 = getIntent();
        u_id = intent1.getStringExtra("u_id");
        Log.i("TAG", "onCreate: 제발찍히세요" + u_id);
        board.setB_dtt(b_dtt);
        Log.i("TAG", "onCreate: board b_dtt 확인" + board);


        tv_view_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b_u_id.equals(u_id)) {
                    Toast.makeText(CommunityViewActivity.this, "삭제할 권한이 없습니다", Toast.LENGTH_SHORT).show();
                } else {
                    dataService.boardApi.removeBoard(board).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            }
        });

        // 수정
        tv_view_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b_u_id.equals(u_id)) {
                    Toast.makeText(CommunityViewActivity.this, "수정할 권한이 없습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent2 = new Intent(CommunityViewActivity.this, CommunityUpdateActivity.class);
                    intent2.putExtra("b_dtt", b_dtt);
                    intent2.putExtra("u_id", u_id);
                    startActivity(intent2);
                }
            }
        });

    }

}