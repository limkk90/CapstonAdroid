package org.yju.myapplication.qna;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.yju.myapplication.DataService;
import org.yju.myapplication.R;
import org.yju.myapplication.data.Question;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QnaOneonOne extends Fragment {
    View view;
    RadioButton qna_radio_ching, qna_radio_bool, qna_radio_jean, qna_radio_bug;
    RadioGroup radioGroup2;
    EditText qna_editText_content, qna_editText_title;
    Button btn_qna;
    DataService dataService = new DataService();
    String  title, content, u_id;
    char q_cat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.qna_insert, container, false);

        Bundle bundle = getArguments();
        Log.i("TAG", "onCreateView: 마지막값" + bundle);

        qna_editText_content = view.findViewById(R.id.qna_editText_content);
        qna_editText_title = view.findViewById(R.id.qna_editText_title);
        btn_qna = view.findViewById(R.id.btn_qna);
        radioGroup2 = view.findViewById(R.id.radioGroup2);
        qna_radio_bool = view.findViewById(R.id.qna_radio_bool);
        qna_radio_bug = view.findViewById(R.id.qna_radio_bug);
        qna_radio_jean = view.findViewById(R.id.qna_radio_jean);
        qna_radio_ching = view.findViewById(R.id.qna_radio_ching);

        btn_qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(qna_radio_ching.isChecked())
                    q_cat = '0';
                if (qna_radio_bool.isChecked())
                    q_cat = '1';
                if (qna_radio_jean.isChecked())
                    q_cat = '2';
                if (qna_radio_bug.isChecked())
                    q_cat = '3';

                title = qna_editText_title.getText().toString();
                content = qna_editText_content.getText().toString();
                u_id = bundle.getString("u_id");
                Question question = new Question();
                question.setQ_cat(q_cat);
                question.setQ_title(title);
                question.setQ_content(content);
                question.setU_id(u_id);

                try {
                    if (!u_id.equals(null)) {
                        dataService.qnaApi.setQ(question).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(getActivity(), "등록되었습니다", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "로그인을 해주세요", Toast.LENGTH_LONG).show();
                }


            }
        });


        return view;
    }
}
