
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
        Log.i("TAG", "onCreate: ??????????????? ?????????????" + b_u_id);

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

        // ????????? ??????
        Intent intent1 = getIntent();
        u_id = intent1.getStringExtra("u_id");
        Log.i("TAG", "onCreate: ??????????????????" + u_id);
        board.setB_no(b_no);
        Log.i("TAG", "onCreate: board b_dtt ??????" + board);

        tv_view_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b_u_id.equals(u_id)) {
                    Toast.makeText(CommunityViewActivity.this, "????????? ????????? ????????????", Toast.LENGTH_SHORT).show();
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

        // ????????? ??????
        tv_view_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b_u_id.equals(u_id)) {
                    Toast.makeText(CommunityViewActivity.this, "????????? ????????? ????????????", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent2 = new Intent(CommunityViewActivity.this, CommunityUpdateActivity.class);
                    intent2.putExtra("b_no", b_no);
                    intent2.putExtra("u_id", u_id);
                    startActivity(intent2);
                }
            }
        });

        // ????????????
        //????????? ????????? ?????? ????????? ??????
        btn_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r_content = et_reply.getText().toString();

                if(r_content == null){
                    Toast.makeText(CommunityViewActivity.this, "????????? ?????? ????????????", Toast.LENGTH_SHORT).show();
                    return;
                }
                reply.setR_content(r_content);
                reply.setR_writer(u_id);
                if(u_id ==null){
                    Toast.makeText(CommunityViewActivity.this, "???????????? ????????????", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.i("TAG", "onClick: u_id ??????????" + u_id);
                reply.setB_no(b_no);
                Log.i("TAG", "onClick: b_no" + b_no);
                dataService.boardApi.replyAdd(reply).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(CommunityViewActivity.this, "???????????????????????????", Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "onResponse: ??????????????????" + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("TAG", "onResponse: ??????????????????" );

                    }
                });
            }
        });

        // ??????
        btn_warn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(CommunityViewActivity.this, CommunityWarningActivity.class);
                intent3.putExtra("u_id", b_u_id);
                startActivity(intent3);
            }
        });

//        // ????????????
//        dataService.boardApi.replyDelete(reply).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Log.i("ReplyAdapter", "??????????????????: ");
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
        // ?????????
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

                Log.i("boardView", "?????????: " + board);
//                Log.i("boardView", "????????????: " + replylist.get(0));

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
        // ?????????????????? ???????????? ??? ????????????, ???????????? ???????????????.
    }
}