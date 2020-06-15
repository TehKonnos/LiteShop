package com.example.liteshop20;

import androidx.room.Database;
import androidx.room.RoomDatabase;
//Δήλωση βάσης δεδομένων και δήλωση πινάκων.
@Database(entities = {User.class,Product.class,Orders.class,ProdCart.class},version =1,exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
