package com.example.personalmanagementapp;

import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends ListAdapter<ModelClass, RecyclerViewAdapter.MyViewholder> {
    MainActivity5 mainActivity5;
    protected RecyclerViewAdapter(MainActivity5 mainActivity5) {
        super(ModelClass.modelClassItemCallback);
        this.mainActivity5=mainActivity5;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addfriernd_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.tv_Fname.setText(getCurrentList().get(position).getFirstName());
        holder.tv_Lname.setText(getCurrentList().get(position).getLastName());
        holder.tv_Age.setText(String.valueOf(getCurrentList().get(position).getAge()));

        holder.tv_Address.setText(getCurrentList().get(position).getAddress());
        if(getCurrentList().get(position).getGender()==0){
            holder.tv_Gender.setText("Male");
        }
        else{
            holder.tv_Gender.setText("Female");
        }


    }

    @Override
    public void submitList(@Nullable List<ModelClass> list) {
        super.submitList(list == null ? null : new ArrayList<ModelClass>(list));
    }

    class MyViewholder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
       // TextView firstname;
        TextView tv_Fname,tv_Lname,tv_Age,tv_Gender,tv_Address;
        ImageView iv_Delete,iv_Edit;
       // ImageView deleteImageView;
       // ImageView editimageView;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            tv_Fname=itemView.findViewById(R.id.tv_rec_fname);
            tv_Lname=itemView.findViewById(R.id.tv_rec_lname);
            tv_Age=itemView.findViewById(R.id.tv_rec_age);
            tv_Gender=itemView.findViewById(R.id.tv_rec_gender);
            tv_Address=itemView.findViewById(R.id.tv_rec_address);
            iv_Delete=itemView.findViewById(R.id.iv_rec_delete);
            iv_Edit=itemView.findViewById(R.id.iv_rec_edit);
//            firstname = itemView.findViewById(R.id.recyclerviewFirstTextview);
//            deleteImageView=itemView.findViewById(R.id.deleteimageview);
//            editimageView=itemView.findViewById(R.id.editimageview);
            iv_Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.getInstance(mainActivity5.getApplication()).dao().deleteData(getCurrentList().get(getAdapterPosition()));
                }
            });

          iv_Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent intent=new Intent(mainActivity5,EditActivity.class);
                   intent.putExtra("id",getCurrentList().get(getAdapterPosition()).getId());
                   mainActivity5.startActivity(intent);
                }
            });
        }
    }
}
