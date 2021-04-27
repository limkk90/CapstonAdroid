package org.yju.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPwActivity extends AppCompatActivity {

    private EditText et_findPw_id, et_pw_email, et_pw_emailOk;
    private Button btn_email_post, btn_email_get, btn_pwUpdate;
    private Email email = new Email();
    private User user = new User();
    DataService dataService = new DataService();
    private boolean a = true;
    private String u_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);

        et_findPw_id = findViewById(R.id.et_findPw_id);
        et_pw_email = findViewById(R.id.et_pw_email);
        et_pw_emailOk = findViewById(R.id.et_pw_emailOk);

        btn_email_post = findViewById(R.id.btn_email_post);
        btn_email_get = findViewById(R.id.btn_email_get);
        btn_pwUpdate = findViewById(R.id.btn_pwUpdate);

        btn_email_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_email = et_pw_email.getText().toString();
                email.setUserEmail(u_email);
                dataService.select.sendMail(email).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("TAG", "onResponse: 이메일테스트1@#!@#!@#" + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                Toast.makeText(FindPwActivity.this, "이메일 인증을 전송하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 인증번호 확인
        btn_email_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailOk = et_pw_emailOk.getText().toString();
                email.setConfirm(emailOk);
                dataService.select.confirm(email).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() == 1){
                            Log.i("TAG", "onResponse: dddddddss" + response.body());
                            Toast.makeText(FindPwActivity.this, "인증되었습니다", Toast.LENGTH_SHORT).show();
                            a = true;
                        }else{
                            Toast.makeText(FindPwActivity.this, "인증번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                            a = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });

        btn_pwUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_findPw_id.getText().toString();
                String email = et_pw_email.getText().toString();
                String emailOk = et_pw_emailOk.getText().toString();
                user.setU_id(id);
                if (id.isEmpty()) {
                    Toast.makeText(FindPwActivity.this, "아이디가 없습니다", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(FindPwActivity.this, "이메일이 없습니다", Toast.LENGTH_SHORT).show();
                } else if (emailOk.isEmpty()) {
                    Toast.makeText(FindPwActivity.this, "인증번호가 없습니다", Toast.LENGTH_SHORT).show();
                } else if (a == true) {
                    dataService.select.prifile(id).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.i("TAG", "onResponse: 아이디들어옴?" + response.body());
                        u_id = response.body().getU_id();
                        Intent intent = new Intent(FindPwActivity.this, UpdatePwActivity.class);
                        intent.putExtra("id", u_id);
                        Log.i("TAG", "onClick: putput" + u_id);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(FindPwActivity.this, "아이디을 확인해주세요", Toast.LENGTH_LONG).show();

                    }
                });
                }


            }
        });


    }
}