package com.example.personalmanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {

    private EditText et_edit_taskname,et_edit_loc;
    private CheckBox cb_edit_completd,cb_edit_uncomplted;
    private Button bt_edit_save;
    int taskmodelClassId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_layout);

        et_edit_taskname=(EditText) findViewById(R.id.edit_TaskName);
        et_edit_loc=(EditText)findViewById(R.id.edit_Loc);
        cb_edit_completd=(CheckBox)findViewById(R.id.edit_checkBox3);
        cb_edit_uncomplted=(CheckBox)findViewById(R.id.edit_checkBox4);
        bt_edit_save=(Button)findViewById(R.id.edit_save);
        taskmodelClassId=getIntent().getIntExtra("id",-1);

     TaskModelClass taskModelClass=   Database.getInstance(getApplication()).dao().getTaskById(taskmodelClassId);
        et_edit_taskname.setText(taskModelClass.getTaskname());
        et_edit_loc.setText(taskModelClass.getLocation());
        if(taskModelClass.getTask()==0){
            cb_edit_completd.setChecked(true);
            cb_edit_uncomplted.setChecked(false);
        }
        else{
            cb_edit_uncomplted.setChecked(true);
            cb_edit_completd.setChecked(false);
        }


       // bt_veiwTask=(Button)findViewById(R.id.view1);
        cb_edit_completd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                    cb_edit_uncomplted.setClickable(false);
                else cb_edit_uncomplted.setClickable(true);
            }
        });
        cb_edit_uncomplted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                    cb_edit_completd.setClickable(false);
                else cb_edit_completd.setClickable(true);
            }
        });

bt_edit_save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        TaskModelClass taskmodelClass=new TaskModelClass();

        taskmodelClass.setTaskname(et_edit_taskname.getText().toString());
        taskmodelClass.setId(taskmodelClassId);
        taskmodelClass.setLocation(et_edit_loc.getText().toString());


        if(cb_edit_completd.isChecked())
            taskmodelClass.setTask(0);
        else taskmodelClass.setTask(1);
        Database.getInstance(getApplication()).dao().updatetodolist(taskmodelClass);
        finish();
    }
});
    }
}
