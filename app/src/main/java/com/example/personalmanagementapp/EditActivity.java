package com.example.personalmanagementapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class EditActivity extends AppCompatActivity {



    EditText fname, lname, age, addr;

    CheckBox maleCheckbox;
    CheckBox femalecheckBox;
    Button SaveButton;
    int modelClassId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);
        SaveButton = (Button) findViewById(R.id.editFragmentSaveButton);
        fname = (EditText) findViewById(R.id.fname_edit);
        lname = (EditText) findViewById(R.id.lname_edit);
        age = (EditText) findViewById(R.id.age_edit);
        addr = (EditText) findViewById(R.id.addr_edit);
        maleCheckbox=findViewById(R.id.checkBox1_edit);
        femalecheckBox=findViewById(R.id.checkBox2_edit);
        modelClassId=getIntent().getIntExtra("id",-1);

        ModelClass modelclass=   Database.getInstance(getApplication()).dao().getDataById(modelClassId);
        fname.setText(modelclass.getFirstName());
        lname.setText(modelclass.getLastName());
        age.setText(String.valueOf(modelclass.getAge()));
        addr.setText(modelclass.getAddress());
        fname.setText(modelclass.getFirstName());
        if(modelclass.getGender()==0){
            maleCheckbox.setChecked(true);
            femalecheckBox.setChecked(false);
        }
        else{
            femalecheckBox.setChecked(true);
            maleCheckbox.setChecked(false);
        }

        maleCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                    femalecheckBox.setClickable(false);
                else femalecheckBox.setClickable(true);
            }
        });


        femalecheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                    maleCheckbox.setClickable(false);
                else maleCheckbox.setClickable(true);
            }
        });
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelClass modelClass=new ModelClass();
                String Names = fname.getText().toString();
                modelClass.setFirstName(Names);
                modelClass.setId(modelClassId);
                modelClass.setLastName(lname.getText().toString());
                modelClass.setAddress(addr.getText().toString());
                modelClass.setAge(Integer.parseInt(age.getText().toString()));
                if(maleCheckbox.isChecked())
                    modelClass.setGender(0);
                else modelClass.setGender(1);
                Database.getInstance(getApplication()).dao().update(modelClass);
                finish();
            }
        });


    }

}