package org.yju.myapplication.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageUserInfoFrag extends Fragment {
    View view;

    private EditText editText_carNumber, editTextTextPersonName, editTextTextPersonName2;
    private Button btn_userInfoChg;
    private String new_Pwd, new_Pwd2, u_car, u_id;
    private boolean a;
    User user = new User();
    DataService dataService = new DataService();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mypage_userinfo, container, false);
        Bundle bundle = getArguments();


        editText_carNumber = view.findViewById(R.id.editText_carNumber);
        editTextTextPersonName = view.findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = view.findViewById(R.id.editTextTextPersonName2);
        btn_userInfoChg = view.findViewById(R.id.btn_userInfoChg);

        btn_userInfoChg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u_id = bundle.getString("u_id");
                new_Pwd = editTextTextPersonName.getText().toString();
                new_Pwd2 = editTextTextPersonName2.getText().toString();
                u_car = editText_carNumber.getText().toString();
                if (!new_Pwd.equals(new_Pwd2)) {
                    Toast.makeText(getActivity(), " 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    a = false;
                } else {
                    a = true;
                }

                user.setU_id(u_id);
                user.setU_pwd(new_Pwd);
                user.setU_car(u_car);

                if (a == true) {

                    dataService.myPageApi.updateUser(user).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(getActivity(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });

        return view;


    }
}
