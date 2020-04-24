package com.example.hooli;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private List<TaskItem> tasks;
    private TaskDatabase db;

    // constuctor
    public TaskAdapter(Context context, List<TaskItem> tasks) {
        this.context = context;
        this.tasks = tasks;
        db = new TaskDatabase(context);
    }


    public void setItemCount(int count) {
        tasks.clear();
        tasks.addAll(get_all_data(count));
        notifyDataSetChanged();
    }

    public void onDeleteItem(int count) {
        tasks.clear();
        tasks.addAll(get_all_data(count));
    }

    public void removeItemSelected(int selected) {
        if (tasks.isEmpty()) {
            return ;
        }
        tasks.remove(selected);
        notifyItemRemoved(selected);
    }


    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new TaskAdapter.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
          TaskItem item = tasks.get(position);

          holder.setDetails(item);
          holder.setActiveImage(item.getBlocking());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements
                         View.OnClickListener, View.OnLongClickListener{

        private TextView recycle_title, recycle_time, recycle_date;
        private ImageView activeImage;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            itemView.setLongClickable(true);

            recycle_title = itemView.findViewById(R.id.recycle_title);
            recycle_time = itemView.findViewById(R.id.recycle_time);
            recycle_date = itemView.findViewById(R.id.recycle_date);
            activeImage = itemView.findViewById(R.id.active_image);

        }

        public void setDetails(TaskItem Item) {
            recycle_title.setText(Item.getTitle());
            recycle_time.setText(Item.getTime());
            recycle_date.setText(Item.getDate());

        }

        public void setActiveImage(String active) {

            if(active.equals("true")) {
                activeImage.setImageResource(R.drawable.ic_notifications_on_black_24dp);
            } else if (active.equals("false")) {
                activeImage.setImageResource(R.drawable.ic_notifications_off_grey600_24dp);
            }
        }


        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }


    // get database data, sort and show properly
    public List<TaskItem> get_all_data(int count) {
        ArrayList<TaskItem> items = new ArrayList<>();

        List<TaskItem> tasks = db.getAllTasks();

        List<String> Titles = new ArrayList<>();
        List<String> Date = new ArrayList<>();
        List<String> Time = new ArrayList<>();
        List<String> Completed = new ArrayList<>();
        List<String> Blocking = new ArrayList<>();


        return tasks;

    }

}
