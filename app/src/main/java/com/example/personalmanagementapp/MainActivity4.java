package com.example.personalmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity4 extends AppCompatActivity {

    private EditText et_taskname,et_loc;
    private CheckBox cb_completd,cb_uncomplted;
    private Button bt_addTask,bt_veiwTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        et_taskname=(EditText) findViewById(R.id.TaskName);
        et_loc=(EditText)findViewById(R.id.Loc);
        cb_completd=(CheckBox)findViewById(R.id.checkBox3);
        cb_uncomplted=(CheckBox)findViewById(R.id.checkBox4);
        bt_addTask=(Button)findViewById(R.id.addbtn2);
        bt_veiwTask=(Button)findViewById(R.id.view1);
        cb_uncomplted.setChecked(true);
       cb_completd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(compoundButton.isChecked())
                   cb_uncomplted.setClickable(false);
               else cb_uncomplted.setClickable(true);
           }
       });
       cb_uncomplted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(compoundButton.isChecked())
                   cb_completd.setClickable(false);
                else cb_completd.setClickable(true);
           }
       });
bt_addTask.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(et_taskname.getText().toString()==null){
            et_taskname.setError("Enter the Task Name");
        }
        else if(et_loc.getText().toString()==null){
            et_loc.setError("Enter the Task Name");
        }
        else
        {
            TaskModelClass tmc=new TaskModelClass();

            tmc.setTaskname(et_taskname.getText().toString());
            tmc.setLocation(et_loc.getText().toString());
            if(cb_completd.isChecked())
                tmc.setTask(0);
            else tmc.setTask(1);
            Database.getInstance(getApplication()).dao().inserttodolist(tmc).toString();
            et_taskname.setText("");
            et_loc.setText("");
            cb_completd.setChecked(false);
            cb_uncomplted .setChecked(false);
            Toast.makeText(MainActivity4.this, "\n You have added a task in your record",
                    Toast.LENGTH_LONG).show();
        }

    }
});

    }


    public void moveFinal(View view){
        Intent intent = new Intent(MainActivity4.this, MainActivity6.class);

        startActivity(intent);
    }

}

