package org.yju.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePwActivity extends AppCompatActivity {

    DataService dataService = new DataService();
    private String u_id;
    private EditText et_upPwd, et_upPwd2;
    private Button btn_updatePw;
    private Boolean a = true;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pw);

        et_upPwd = findViewById(R.id.et_upPwd);
        et_upPwd2 = findViewById(R.id.et_upPwd2);
        btn_updatePw = findViewById(R.id.btn_updatePw);

        Intent intent = getIntent();
        u_id = intent.getStringExtra("id");

        btn_updatePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPwd = et_upPwd.getText().toString();
                String newPwd2 = et_upPwd2.getText().toString();
                if (!newPwd.equals(newPwd2)) {
                    Toast.makeText(org.yju.myapplication.UpdatePwActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    a = false;
                } else {
                    Toast.makeText(org.yju.myapplication.UpdatePwActivity.this, "변경되었습니다", Toast.LENGTH_SHORT).show();
                    a = true;
                }
                user.setU_id(u_id);
                user.setU_pwd(newPwd);

                if (a == true) {
                    dataService.update.update(user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                    Intent intent = new Intent(org.yju.myapplication.UpdatePwActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}