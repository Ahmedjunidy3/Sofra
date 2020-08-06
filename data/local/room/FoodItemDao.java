package com.example.sofra.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodItemDao {

    @Insert
    void insert(FoodItem item);

    @Query("DELETE FROM food_items WHERE id_Col = :id")
    void delete(int id);

    @Query("DELETE FROM food_items")
    void deleteAllItems();

    @Query("SELECT * FROM food_items")
    LiveData<List<FoodItem>> getAllItems();
}
