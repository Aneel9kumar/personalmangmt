package com.example.personalmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    ListView FriendView1;
    ArrayList list;
    Button addbtn1;
    EditText fname, lname, age, addr;
    ArrayAdapter<String> arrayAdapter;
    CheckBox maleCheckbox;
    CheckBox femalecheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        addbtn1 = (Button) findViewById(R.id.addbtn1);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        age = (EditText) findViewById(R.id.age);
        addr = (EditText) findViewById(R.id.addr);
        maleCheckbox=findViewById(R.id.checkBox1);
        femalecheckBox=findViewById(R.id.checkBox2);

        list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, list);
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
        addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fname.getText().toString().isEmpty()){
                   fname.setError("Enter First Name");
                }
                else if(lname.getText().toString().isEmpty()){
                    lname.setError("Enter last Name");
                }
                else if(age.getText().toString().isEmpty()){
                    age.setError("Enter Your Age");
                }
                else if(addr.getText().toString().isEmpty()){
                    addr.setError("Enter Your Address");
                }
//                else if(maleCheckbox.isChecked()){
//                    age.setError("pl");
//                }
              //  else if ()
                else{
                    ModelClass modelClass=new ModelClass();
                    String Names = fname.getText().toString();
                    modelClass.setFirstName(Names);
                    modelClass.setLastName(lname.getText().toString());
                    modelClass.setAddress(addr.getText().toString());
                    modelClass.setAge(Integer.parseInt(age.getText().toString()));
                    if(maleCheckbox.isChecked())
                        modelClass.setGender(0);
                    else modelClass.setGender(1);
                    Database.getInstance(getApplication()).dao().insertdata(modelClass).toString();
                    fname.setText("");
                    lname.setText("");
                    age.setText("");
                    addr.setText("");
                    maleCheckbox.setChecked(false);
                    femalecheckBox.setChecked(false);

                    Toast.makeText(MainActivity3.this, "\n You have added a friend in your record",
                            Toast.LENGTH_LONG).show();
                }


            }

        });


    }

    public void showToast(View view) {

        Toast.makeText(MainActivity3.this, "\n You have added a friend in your record",
                Toast.LENGTH_LONG).show();
    }

    public void moveTask(View view) {
        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
        startActivity(intent);

    }

    public void moveFriend(View view) {
        Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
        startActivity(intent);
    }
}



