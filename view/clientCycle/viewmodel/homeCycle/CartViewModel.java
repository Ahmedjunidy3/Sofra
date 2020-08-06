package com.example.sofra.view.clientCycle.viewmodel.homeCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.sofra.data.local.room.FoodItem;
import com.example.sofra.repository.RoomRepository;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private RoomRepository roomRepository;

    public CartViewModel(@NonNull Application application) {
        super(application);
        roomRepository = new RoomRepository(application);
    }

    public void addReviewedFoodItemToDataBase(FoodItem item) {
        insertItemInRoom(item);
    }

    private void insertItemInRoom(FoodItem item) {
        roomRepository.insertItem(item);
    }

    public LiveData<List<FoodItem>> getMyFoodItems() {
         return getFoodItemsFromRoom();
    }

    private LiveData<List<FoodItem>> getFoodItemsFromRoom() {
        return roomRepository.getItems();
    }

    public void deleteSelectedItemFromCart(int id) {
        deleteItemFromRoom(id);
    }

    public void deleteOrderedFoodItemsFromCart() {
        deleteAllItemsFromRoom();
    }

    private void deleteAllItemsFromRoom() {
      roomRepository.deleteItems();
    }


    private void deleteItemFromRoom(int id) {
        roomRepository.deleteItem(id);
    }


}
