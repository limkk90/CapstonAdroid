
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
import android.widget.EditText;
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
    private Button tv_view_update, tv_view_delete, btn_reply, btn_warn;
    DataService dataService = new DataService();
    private EditText et_reply;
    private String title, content, r_content;
    private String b_no, b_u_id, u_id;
    Board board = new Board();
    RecyclerView recyclerView = null;
    ReplyAdapter replyAdapter = null;
    ArrayList<Reply> rList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager = null;
    TextView reply_delete;
    Reply reply = new Reply();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_view);

        tv_b_title = findViewById(R.id.tv_b_title);
        tv_b_content = findViewById(R.id.tv_b_content);
        tv_view_update = findViewById(R.id.tv_view_update);
        tv_view_delete = findViewById(R.id.tv_view_delete);
        et_reply = findViewById(R.id.et_reply);
        btn_reply = findViewById(R.id.btn_reply);
        btn_warn = findViewById(R.id.btn_warn);


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

        replyAdapter.setOnItemClickListener(new ReplyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Reply reply = replyAdapter.getItem(pos);
                Log.i("ReplyAdapterEvent", "onItemClick: " + reply);

            }
        });

        // 게시글 삭제
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

        // 게시글 수정
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

        // 댓글등록
        //로그인 안하면 댓글 못달게 수정
        btn_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r_content = et_reply.getText().toString();

                if(r_content == null){
                    Toast.makeText(CommunityViewActivity.this, "내용을 입력 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                reply.setR_content(r_content);
                reply.setR_writer(u_id);
                if(u_id ==null){
                    Toast.makeText(CommunityViewActivity.this, "로그인을 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.i("TAG", "onClick: u_id 찍히냐?" + u_id);
                reply.setB_no(b_no);
                Log.i("TAG", "onClick: b_no" + b_no);
                dataService.boardApi.replyAdd(reply).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(CommunityViewActivity.this, "작성완료되었습니다", Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "onResponse: 댓글등록성공" + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("TAG", "onResponse: 댓글등록실패" );

                    }
                });
            }
        });

        // 신고
        btn_warn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(CommunityViewActivity.this, CommunityWarningActivity.class);
                intent3.putExtra("u_id", b_u_id);
                startActivity(intent3);
            }
        });

//        // 댓글삭제
//        dataService.boardApi.replyDelete(reply).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Log.i("ReplyAdapter", "댓글삭제성공: ");
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // 글조회
        reply_delete = findViewById(R.id.reply_delete);
        dataService.boardApi.gBoard(b_no).enqueue(new Callback<Map<String,Object>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Map<String,Object>> call, Response<Map<String,Object>> response) {
                Board board = new Board();
                board = board.ObjToBoard(response.body().get("board"));

                Object o = response.body().get("replyList");
                ObjectMapper mapper = new ObjectMapper();
                ArrayList replylist = mapper.convertValue(o, ArrayList.class);

                Log.i("boardView", "글조회: " + board);
//                Log.i("boardView", "댓글목록: " + replylist.get(0));

                try {
                    if (!replylist.equals(null)) {
                        Reply reply = new Reply();
                        for(int i=0; i<replylist.size(); i++){
                            reply = reply.ObjToReply(replylist.get(i));

                            addItem(reply.getR_content(), reply.getR_writer(), reply.getR_dtt(), reply.getB_no());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItem(String content, String writer, String regDate, String b_no){
        Reply item = new Reply();
        item.setR_content(content);
        item.setR_writer(writer);
        Log.i("ReplyAddItem", "addItem: " + regDate);
        item.setR_dtt(regDate);
        item.setB_no(b_no);
        rList.add(item);
        replyAdapter.notifyDataSetChanged();
        // 리사이클러뷰 어뎁터로 값 넘겨주고, 새로고침 시켜줘야됨.
    }
}