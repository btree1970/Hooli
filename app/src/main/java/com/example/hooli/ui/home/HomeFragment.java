package com.example.hooli.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hooli.AddTaskActivity;
import com.example.hooli.R;
import com.example.hooli.TaskAdapter;
import com.example.hooli.TaskItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recycleView;
    private TaskAdapter adapter;
    private ArrayList<TaskItem> TaskItems;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton AddTaskButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        AddTaskButton = (FloatingActionButton) root.findViewById(R.id.floatingActionButton);

        AddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddTaskActivity.class);
                startActivity(intent);
            }
        });
        recycleView = (RecyclerView) root.findViewById(R.id.recycle_view);

        recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());

        recycleView.setLayoutManager(layoutManager);


        TaskItems = new ArrayList<>();
        adapter = new TaskAdapter(getActivity(), TaskItems);
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
        recycleView.setAdapter(adapter);
//        createListData();

        return root;
    }

//    private void createListData() {
//        TaskItem  item = new TaskItem("Eat Food",  "Make coconunt");
//        TaskItems.add(item);
//        item = new TaskItem("Meeting",  "Get Coconut");
//        TaskItems.add(item);
//    }

}
