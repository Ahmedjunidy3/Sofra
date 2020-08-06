package com.example.sofra.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.sofra.data.local.room.FoodItem;
import com.example.sofra.data.local.room.FoodItemDao;
import com.example.sofra.data.local.room.RoomManager;

import java.util.List;

public class RoomRepository {
    private final FoodItemDao dao;

    public RoomRepository(Application application) {
        RoomManager db = RoomManager.getDataBase(application);
        dao = db.foodItemDao();
    }

    public void insertItem(FoodItem item) {
        new Thread(() -> dao.insert(item)).start();
    }

    public LiveData<List<FoodItem>> getItems() {
        return dao.getAllItems();
    }

    public void deleteItem(int id) {
        new Thread(() -> dao.delete(id)).start();
    }

    public void deleteItems() {
        new Thread(dao::deleteAllItems).start();
    }


}
