package com.example.liteshop20;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "users")
//Κλάση που ορίζουμε πώς θα είναι ο πίνακας users
public class User {
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "uid")

    private int id;

    @ColumnInfo(name="users_name")
    private String name;

    @ColumnInfo (name="users_address")
    private String address;

    @ColumnInfo (name="users_phone")
    private String phoneNum;

    @ColumnInfo (name="users_notes")
    private String notes="";

    @ColumnInfo (name="users_town")
    private String town;

    @ColumnInfo (name="users_postCode")
    private String postCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
