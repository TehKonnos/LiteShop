package com.example.liteshop20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QueryProduct extends Fragment {
    TextView textView;
    public QueryProduct() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_query_product, container, false);

        textView = view.findViewById(R.id.textViewProducts);
        List<Product> products =MainActivity.myAppDatabase.myDao().getProducts();
        String result ="Τα προϊόντα στη βάση είναι: \n ----------------------- \n";
        for (Product i: products){ //Αποθηκεύουμε ένα ένα τα αποτελέσματα και μετά τα εμφανίζουμε χρησιμοποιόντας το textView
            int id = i.getId();
            String title = i.getTitle();
            String description =i.getDescription();
            int price = i.getPrice();
            int stack =i.getStack();

            result =result + "\n ID: "+id+"\n Τίτλος: "+ title +"\n Περιγραφή: "+description+"\n Τιμή: " +price +"\n Απόθεμα: "+ stack+ "\n --------------------------\n";

        }
        textView.setText(result);
        return view;
    }
}
