
package org.yju.myapplication.Community;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;
import org.yju.myapplication.data.Reply;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CommunityViewActivity extends AppCompatActivity {

    private TextView tv_b_title, tv_b_content;
    private Button tv_view_update, tv_view_delete;
    DataService dataService = new DataService();
    private String title;
    private String content;
    private String b_no, b_u_id, u_id;
    Board board = new Board();
    RecyclerView recyclerView = null;
    ReplyAdapter replyAdapter = null;
    ArrayList<Reply> rList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_view);

        tv_b_title = findViewById(R.id.tv_b_title);
        tv_b_content = findViewById(R.id.tv_b_content);
        tv_view_update = findViewById(R.id.tv_view_update);
        tv_view_delete = findViewById(R.id.tv_view_delete);

        final Intent[] intent = {getIntent()};
        b_no = (intent[0].getExtras().getString("b_no"));
        b_u_id = intent[0].getExtras().getString("b_u_id");
        Log.i("TAG", "onCreate: 유저아이디 넘어오냐?" + b_u_id);

        recyclerView = findViewById(R.id.reply_RecyclerView);
        replyAdapter = new ReplyAdapter(rList);
        recyclerView.setAdapter(replyAdapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);



        // 삭제

        Intent intent1 = getIntent();
        u_id = intent1.getStringExtra("u_id");
        Log.i("TAG", "onCreate: 제발찍히세요" + u_id);
        board.setB_no(b_no);
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
                    intent2.putExtra("b_no", b_no);
                    intent2.putExtra("u_id", u_id);
                    startActivity(intent2);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 글 조회
        dataService.boardApi.gBoard(b_no).enqueue(new Callback<Map<String,Object>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Map<String,Object>> call, Response<Map<String,Object>> response) {
                Board board = new Board();
                board = board.ObjToBoard(response.body().get("board"));

                Object o = response.body().get("replyList");
                ObjectMapper mapper = new ObjectMapper();
                ArrayList replylist = mapper.convertValue(o, ArrayList.class);

                Log.i("boardView", "onResponse: " + board);
                Log.i("boardView", "onResponse: " + replylist.get(0));

                Reply reply = new Reply();
                for(int i=0; i<replylist.size(); i++){
                    reply = reply.ObjToReply(replylist.get(i));
                    Log.i("SuccessboardView", "onResponse: "+ reply.getR_content());
                    Log.i("SuccessboardView", "onResponse: "+ reply.getR_writer());
                    Log.i("SuccessboardView", "onResponse: "+ reply.getR_dtt());

                    addItem(reply.getR_content(), reply.getR_writer(), reply.getR_dtt());
                }

                title =  board.getB_title();
                content = board.getB_content();
                tv_b_title.setText(title);
                tv_b_content.setText(content);

            }

            @Override
            public void onFailure(Call<Map<String,Object>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        rList.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String content, String writer, String regDate){
        Reply item = new Reply();
        item.setR_content(content);
        item.setR_writer(writer);
        item.setR_dtt(regDate);
        rList.add(item);
        replyAdapter.notifyDataSetChanged();
        // 리사이클러뷰 어뎁터로 값 넘겨주고, 새로고침 시켜줘야됨.
    }
}
