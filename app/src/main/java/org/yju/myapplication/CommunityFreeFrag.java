package org.yju.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.yju.myapplication.R;
import org.yju.myapplication.data.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommunityFreeFrag extends Fragment {
    private View view;
    RecyclerView recyclerView = null;
    CommunityAdapter adapter = null;
    ArrayList<Board> bList = new ArrayList<Board>();
    LinearLayoutManager linearLayoutManager = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community_free, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        adapter = new CommunityAdapter(bList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        addItem("김", "김", "임");
        adapter.notifyDataSetChanged();
        return view;


    }

    public void addItem(String title, String content, String writer){
        Board item = new Board();
        item.setB_title(title);
        item.setB_content(content);
        item.setU_id(writer);
        bList.add(item);
    }
}
