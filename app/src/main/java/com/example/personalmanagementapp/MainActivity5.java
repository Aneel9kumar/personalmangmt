package com.example.personalmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.personalmanagementapp.databinding.ActivityMain5Binding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MainActivity5 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerViewAdapter=new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        Database.getInstance(getApplication()).dao().getTotalData().observe(this, new Observer<List<ModelClass>>() {
            @Override
            public void onChanged(List<ModelClass> modelClasses) {
                recyclerViewAdapter.submitList(modelClasses);
            }
        });

    }


}