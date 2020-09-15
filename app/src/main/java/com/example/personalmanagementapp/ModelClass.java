package com.example.personalmanagementapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ModelClass {
    private String firstName;
    private String LastName;
    private int age;
    private int gender;
    private String address;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static DiffUtil.ItemCallback<ModelClass>modelClassItemCallback=new DiffUtil.ItemCallback<ModelClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull ModelClass oldItem, @NonNull ModelClass newItem) {
            return oldItem.id==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ModelClass oldItem, @NonNull ModelClass newItem) {
            return oldItem.getFirstName().equals(newItem.getFirstName());
        }
    };
}
