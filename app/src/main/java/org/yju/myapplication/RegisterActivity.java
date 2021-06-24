package org.yju.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.yju.myapplication.data.Email;
import org.yju.myapplication.data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_email, et_phone, et_emailOk, et_car;
    private Button btn_register, btn_emailSend, btn_emailOk;
    DataService dataService = new DataService();
    private User user = new User();
    private boolean a = true;
    private boolean b = true;
    private Email email = new Email();

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 시작시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // 아이디 값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_emailOk = findViewById(R.id.et_emailOk);
        et_car = findViewById(R.id.et_car);

        et_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // 아이디 중복검사
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                dataService.select.prifile(et_id.getText().toString()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.i("TAG", "onResponse: 아이디중복검사&&&&" + response.body());
                        try {
                            if (et_id.getText().toString().equals(response.body().getU_id())) {
                                Toast.makeText(RegisterActivity.this, "아이디 중복입니다", Toast.LENGTH_SHORT).show();
//                                Snackbar.make(, "아이디 중복입니다.", Snackbar.LENGTH_LONG).show();
                                a = false;
                            }


                        } catch (Exception e) {
                            a = true;
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // 이메일 보내기
        btn_emailSend = findViewById(R.id.btn_emailSend);
        btn_emailSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_email = et_email.getText().toString();
                email.setUserEmail(u_email);
                dataService.select.sendMail(email).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("TAG", "onResponse: 이메일테스트1@#!@#!@#" + response.body());
                        Toast.makeText(RegisterActivity.this, "이메일 인증을 전송하였습니다.", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

        // 인증번호 확인
        btn_emailOk = findViewById(R.id.btn_emailOk);
        btn_emailOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailOk = et_emailOk.getText().toString();
                email.setConfirm(emailOk);
                dataService.select.confirm(email).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() == 1){
                            Log.i("TAG", "onResponse: dddddddss" + response.body());
                            Toast.makeText(RegisterActivity.this, "인증되었습니다", Toast.LENGTH_SHORT).show();
                            b = true;
                        }else{
                            Toast.makeText(RegisterActivity.this, "인증번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                            b = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });


        // 회원가입 버튼 클릭 시 수행
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String u_id = et_id.getText().toString();
                String u_pwd = et_pass.getText().toString();
                String u_email = et_email.getText().toString();
                String u_phone = et_phone.getText().toString();
                String u_ok = et_emailOk.getText().toString();
                String u_car = et_car.getText().toString();

                user.setU_id(u_id);
                user.setU_email(u_email);
                user.setU_pwd(u_pwd);
                user.setU_car(u_car);


                if (u_id.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "아이디가 없습니다", Toast.LENGTH_SHORT).show();

                } else if (a == false){
                    Toast.makeText(RegisterActivity.this, "아이디 중복입니다", Toast.LENGTH_SHORT).show();

                } else if (b == false){
                    Toast.makeText(RegisterActivity.this, "인증번호를 확인해주세요", Toast.LENGTH_SHORT).show();

                } else if (u_pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "패스워드가 없습니다", Toast.LENGTH_SHORT).show();

                } else if (u_email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "이메일이 없습니다", Toast.LENGTH_SHORT).show();

                } else if (u_ok.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "인증을 부탁합니다", Toast.LENGTH_SHORT).show();

                } else if (u_phone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "폰넘버가 없습니다", Toast.LENGTH_SHORT).show();

                } else if (a == true) {
                    dataService.insert.signup(user).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }


            }
        });

    }
}