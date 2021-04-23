package org.yju.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register, btn_find, btn_pwFind;
    DataService dataService = new DataService();
    private User user = new User();
    private String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_find = findViewById(R.id.btn_find);
        btn_pwFind = findViewById(R.id.btn_pwFind);


        // 회원가입 버튼을 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(org.yju.myapplication.LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 아이디 찾기
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(org.yju.myapplication.LoginActivity.this, FindUserActivity.class);
                startActivity(intent);
            }
        });

        // 비밀번호 찾기
        btn_pwFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(org.yju.myapplication.LoginActivity.this, FindPwActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap();
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String u_id = et_id.getText().toString();
                String u_pwd = et_pass.getText().toString();

                user.setU_id(u_id);
                user.setU_pwd(u_pwd);

                dataService.select.login(user).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Log.i("TAG", "onResponse: 이지아 병" + response.body());
                        try {
                            if (!response.body().isEmpty()) {
                                Log.i("TAG", "onResponse: " + response.body());
                                Intent intent = new Intent(org.yju.myapplication.LoginActivity.this, UserProfileActivity.class);
                                intent.putExtra("u_id", u_id);
                                startActivity(intent);
                            }
                        } catch (NullPointerException e) {
                            Log.i("TAG", "onResponse: 가르쳐주세용" + response.body());
                            Toast.makeText(org.yju.myapplication.LoginActivity.this, "아이디 또는 비밀번호 오류입니다", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });


    }

}