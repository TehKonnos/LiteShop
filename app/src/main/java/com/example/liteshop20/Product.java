package com.example.liteshop20;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Κλάση που ορίζουμε πώς θα είναι ο πίνακας products
@Entity(tableName = "products")
public class Product {
    @PrimaryKey (autoGenerate = true) @ColumnInfo (name = "pid")
    private int id;

    @ColumnInfo (name = "ptitle")
    private String title;

    @ColumnInfo (name = "pprice")
    private int price;

    @ColumnInfo (name = "pstack")
    private int stack;

    @ColumnInfo (name = "pdesc")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    int getPrice() {
        return price;
    }

    void setPrice(int price) {
        this.price = price;
    }

    int getStack() {
        return stack;
    }

    void setStack(int stack) {
        this.stack = stack;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }
}
