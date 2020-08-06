package com.example.sofra.data.local.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_items")
public class FoodItem {

    @ColumnInfo(name = "id_Col")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "restaurantId_Col")
    private String restaurantId;

    @ColumnInfo(name = "itemId_Col")
    private int itemId;

    @ColumnInfo(name = "name_Col")
    private String name;

    @ColumnInfo(name = "qty_Col")
    private String qty;

    @ColumnInfo(name = "price_Col")
    private String price;

    @ColumnInfo(name = "photoUrl_Col")
    private String photoUrl;

    @ColumnInfo(name = "note_Col")
    private String note;

    @ColumnInfo(name = "deliveryCost_Col")
    private String deliveryCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

}
