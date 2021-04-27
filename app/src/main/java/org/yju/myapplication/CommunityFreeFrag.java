package org.yju.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CommunityFreeFrag extends Fragment {
    private View view;
    private FloatingActionButton float_btn_freeWrite;
//    private CommunityFreeBinding cFreeBindg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community_free, container, false);

        FloatingActionButton viewById = view.findViewById(R.id.float_btn_freeWrite);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FreeCommunityInsert.class);
                startActivity(intent);


            }
        });

        return view;
    }
}
