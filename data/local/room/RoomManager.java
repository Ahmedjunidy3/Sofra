package com.example.sofra.data.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {FoodItem.class}, version = 1, exportSchema = false)
public abstract class RoomManager extends RoomDatabase {
    private static RoomManager roomManager;

    public abstract FoodItemDao foodItemDao();

    public synchronized static RoomManager getDataBase(Context context) {
        if (roomManager == null) {
            roomManager = Room.databaseBuilder(context.getApplicationContext(), RoomManager.class
                    , "FoodItem_Database")
                    .build();
        }
        return roomManager;
    }



}
