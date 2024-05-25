package com.mptechprojects.lab5_20193733.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mptechprojects.lab5_20193733.R;
import com.mptechprojects.lab5_20193733.entity.Tasks;

import java.text.MessageFormat;
import java.util.List;
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder>{
    private List<Tasks> tasksList;
    private Context context;
    @NonNull
    @Override
    public TasksAdapter.TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_task, parent, false);
        return new TasksAdapter.TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.TasksViewHolder holder, int position) {
        Tasks t = tasksList.get(position);
        holder.tasks = t;

        TextView textViewName = holder.itemView.findViewById(R.id.tvTaskName);
        textViewName.setText(t.getTaskName());

        TextView textViewDesc = holder.itemView.findViewById(R.id.tvDescription);
        textViewDesc.setText(t.getTaskDescription());

        TextView textViewDate = holder.itemView.findViewById(R.id.tvDate);
        textViewDate.setText(t.getTaskDate());

        TextView textViewPriority = holder.itemView.findViewById(R.id.tvPriority);
        if(t.getTaskPriority() == null){
            textViewPriority.setText("---");
        }else{
            textViewPriority.setText(t.getTaskPriority());
        }
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder {
        Tasks tasks;
        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public List<Tasks> getTasks() {
        return tasksList;
    }

    public void setTasksList(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
