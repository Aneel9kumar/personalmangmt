package com.example.personalmanagementapp;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;
@androidx.room.Database(entities ={ModelClass.class,TaskModelClass.class} ,version = 3,exportSchema = false)

public abstract class Database extends RoomDatabase {
    private static  Database instance=null;
    private static  final Object object=new Object();
    public static Database getInstance(Application applicatio){
        synchronized (object) {
            if (instance == null) {
                instance = Room.databaseBuilder(applicatio, Database.class, "database").allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build();
                return instance;
            }
        }
        return instance;
    }
    public abstract Dao dao();
}
