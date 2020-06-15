package com.example.liteshop20;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import androidx.room.PrimaryKey;
//Κλάση που ορίζουμε πώς θα είναι ο πίνακας prodcart
@Entity(tableName = "prodcart")
public class ProdCart {
    @PrimaryKey @ColumnInfo(name = "pcid")
    private int id;
    @ColumnInfo(name = "pcamount")
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }
}
