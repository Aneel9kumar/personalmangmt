package com.example.personalmanagementapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TaskRecyclerViewAdapter extends ListAdapter<TaskModelClass, TaskRecyclerViewAdapter.MyViewholder> {
    MainActivity6 mainActivity6;
    protected TaskRecyclerViewAdapter(MainActivity6 mainActivity6) {
        super(TaskModelClass.taskmodelClassItemCallback);
        this.mainActivity6=mainActivity6;
    }

    @NonNull
    @Override
    public TaskRecyclerViewAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskRecyclerViewAdapter.MyViewholder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRecyclerViewAdapter.MyViewholder holder, int position) {
        holder.tv_Taskname.setText(getCurrentList().get(position).getTaskname());
        holder.tv_Loc.setText(getCurrentList().get(position).getLocation());
        if(getCurrentList().get(position).getTask()==0){
            holder.tv_Status.setText("Completed");
        }
        else {
            holder.tv_Status.setText("Not Completed");
        }




    }
    @Override
    public void submitList(@Nullable List<TaskModelClass> list) {
        super.submitList(list == null ? null : new ArrayList<TaskModelClass>(list));
    }
    public class MyViewholder extends RecyclerView.ViewHolder {

                TextView tv_Taskname,tv_Loc,tv_Status;
                ImageView iv_delete,iv_edit;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
                tv_Taskname=itemView.findViewById(R.id.tv_trec_tname);
                tv_Loc=itemView.findViewById(R.id.tv_trec_loc);
                tv_Status=itemView.findViewById(R.id.tv_trec_status);
                iv_delete=itemView.findViewById(R.id.iv_trec_delete);
                iv_edit=itemView.findViewById(R.id.iv_trec_edit);

            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.getInstance(mainActivity6.getApplication()).dao().deletetodolist(getCurrentList().get(getAdapterPosition()));

                }
            });
            iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mainActivity6,EditTaskActivity.class);
                    intent.putExtra("id",getCurrentList().get(getAdapterPosition()).getId());
                    mainActivity6.startActivity(intent);
                }
            });
        }
    }
}
