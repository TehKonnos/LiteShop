package com.example.liteshop20;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders",
        primaryKeys = {"ouid","opid","odate"},
        foreignKeys = {
        @ForeignKey(entity = User.class, //Δήλωση ξένου κλειδιού uid απο Users
            parentColumns = "uid",
            childColumns = "ouid",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Product.class, //Δήλωση ξένου κλειδιού pid απο Products
            parentColumns = "pid",
            childColumns = "opid",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)})
public class Orders {
    //Οι αντίστοιχες μεταβλητές που θα είναι πεδία του πίνακα.
    @ColumnInfo (name="ouid") @NonNull
    private int uid;
    @ColumnInfo (name = "opid") @NonNull
    private int pid;
    @ColumnInfo (name = "odate") @NonNull
    private String or_date;
    @ColumnInfo(name = "quantity") @NonNull
    private int or_Quantity;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @NonNull
    public String getOr_date() {
        return or_date;
    }

    public void setOr_date(@NonNull String or_date) {
        this.or_date = or_date;
    }

    public int getOr_Quantity() {
        return or_Quantity;
    }

    public void setOr_Quantity(int or_Quantity) {
        this.or_Quantity = or_Quantity;
    }
}
