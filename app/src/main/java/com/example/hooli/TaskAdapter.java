package com.example.hooli;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TaskItem> tasks;

    // constuctor
    public TaskAdapter(Context context, ArrayList<TaskItem> tasks) {


        this.context = context;
        this.tasks = tasks;
    }


    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return null;
    }




    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView recycle_title, recycle_description;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            recycle_title = itemView.findViewById(R.id.recycle_title);
            recycle_description = itemView.findViewById(R.id.recycle_description);

        }

        public void setDetails(TaskItem Item) {
            recycle_title.setText(Item.getName());
            recycle_description.setText(Item.getDesc());

        }


    }

}
