package com.example.personalmanagementapp;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    public Long insertdata(ModelClass modelClass);
    @Insert
    public Long inserttodolist(TaskModelClass taskModelClass);

    @Delete
    public void deleteData(ModelClass modelClass);
    @Delete
    public void deletetodolist(TaskModelClass taskModelClass);
    @Update
    public void update(ModelClass modelClass);
    @Update
    public  void updatetodolist(TaskModelClass taskModelClass);
    @Query("SELECT *FROM ModelClass ")
    public LiveData<List<ModelClass>> getTotalData();
   @Query("SELECT *FROM TaskModelClass")
   public  LiveData<List<TaskModelClass>> getallTaskData();
    @Query("SELECT * FROM ModelClass WHERE id=:idd")
    public ModelClass getDataById(int idd);
    @Query("SELECT * FROM TaskModelClass WHERE id=:ids")
    public TaskModelClass getTaskById(int ids);
}
