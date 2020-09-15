package com.example.personalmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity6 extends AppCompatActivity {

    private RecyclerView rView;
    private  TaskRecyclerViewAdapter taskRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        rView=(RecyclerView)findViewById(R.id.task_all_recyclerview);
        rView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        taskRecyclerViewAdapter=new TaskRecyclerViewAdapter(this);
        rView.setAdapter(taskRecyclerViewAdapter);
        Database.getInstance(getApplication()).dao().getallTaskData().observe(this, new Observer<List<TaskModelClass>>() {
            @Override
            public void onChanged(List<TaskModelClass> taskmodelClasses) {
                taskRecyclerViewAdapter.submitList(taskmodelClasses);

            }
        });

    }
}