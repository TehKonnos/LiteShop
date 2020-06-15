package com.example.liteshop20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Adapter για τα αντικείμενα "Button" που δημιουργούνται, ανάλογα με τη λίστα που δώσαμε
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<String> products;
    private MyClickListener mOnButtonListener;
    ProductAdapter(ArrayList<String> products, MyClickListener mOnButtonListener) {
        this.products=products;
        this.mOnButtonListener=mOnButtonListener;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        return new ViewHolder(view,mOnButtonListener); //Με το mOnButtonListener ακούω τη θέση του κουμπιού.
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.prodTitle.setText(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView prodTitle;
        MyClickListener myClickListener;

        ViewHolder(@NonNull View itemView, MyClickListener myClickListener) {
            super(itemView);
            prodTitle =itemView.findViewById(R.id.product_title);
            //Αυτό το κάνω για να μπορώ να ξέρω ποιό κουμπί "Product" πατήθηκε, και να το μεταφέρω στο Cart
            //Το ίδιο δουλεύει και στο seeCart για να ξερω ποιό αντικείμενο πατήθηκε και να το μειώνω
            this.myClickListener=myClickListener;
            prodTitle.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mOnButtonListener.onMyClickListener(getAdapterPosition());

        }
    }
    public interface MyClickListener{ //Από εδώ θα ακούμε ποιό κουμπί πατήθηκε στο seeProducts και το SeeCart
       void onMyClickListener(int position);
    }
}
