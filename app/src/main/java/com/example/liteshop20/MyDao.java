package com.example.liteshop20;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//Εντολές για να περαστούν, παρθούν, αλλάξουν οι πίνακες στη βάση.
@Dao
public interface MyDao {
    //--------Users----------------
    @Insert
    void addUser(User user);
    @Query("select * from users")
    List<User> getUsers();
    @Delete
    void deleteUser(User user);
    @Update
    void updateUser(User user);
    @Query("select uid as Var_uid from users where users_name=:name and users_phone=:phone")
    int getUserID(String name,String phone);

    //-------Products-----------------
    @Insert
    void addProduct(Product product);
    @Query("select * from products")
    List<Product> getProducts();
    @Delete
    void deleteProduct(Product product);
    @Update
    void updateProduct(Product products);
    @Query("select pprice from products where pid=:id")
    int getProductPrice(int id);
    @Query("select ptitle from products where pid=:id")
    String getProductTitle(int id);
    @Query("select pstack from products where pid=:id")
    int getProductStack(int id);
    @Query("update products set pstack=:stack where pid=:id")
    void updateProductsStack(int id, int stack);


    //-----------Orders------------------
    @Insert
    void addOrder(Orders order);
    @Query("select * from orders")
     List<Orders> getOrders();
    @Query("delete from orders where ouid=:uid and opid=:pid")
    void deleteOrder(int uid,int pid);
    @Update
    void updateOrder (Orders order);

    //------------Queries--------------------
    @Query("select quantity from orders")
    List<Integer> query2(); //Τις συνολικές πωλήσεις
    @Query("select p.ptitle as titlos,sum(o.quantity) as posotita from orders o INNER JOIN products p ON p.pid=o.opid group by titlos")
    List<Poliseis> query3(); //Τις πωλήσεις ανα προϊόν

    //--------Extra--------
    @Insert
    void addProdCart(ProdCart prodcart);
    @Update
    void updateProdCart(ProdCart prodCart);
    @Query("select pcamount as amount from prodcart where pcid=:id")
    int getProdCartAmount(int id);
    @Query("select * from prodcart")
    List<ProdCart> getProdCart();
    @Delete
    void deleteProdCart(ProdCart prodcart);
}
