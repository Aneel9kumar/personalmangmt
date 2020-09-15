package com.example.personalmanagementapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class TaskModelClass {

    private String taskname;
    private String location;
    private int task;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }









    public static DiffUtil.ItemCallback<TaskModelClass>taskmodelClassItemCallback=new DiffUtil.ItemCallback<TaskModelClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull TaskModelClass oldItem, @NonNull TaskModelClass newItem) {
            return oldItem.id==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskModelClass oldItem, @NonNull TaskModelClass newItem) {
            return oldItem.getTaskname().equals(newItem.getTaskname());
        }
    };


}
