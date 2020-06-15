package com.example.liteshop20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeeProducts extends Fragment implements ProductAdapter.MyClickListener {
    private ArrayList<Integer> ids;
    public SeeProducts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_see_products, container, false);

        ArrayList<String> products = new ArrayList<>();
        ids = new ArrayList<>();
        List<Product> products2 =MainActivity.myAppDatabase.myDao().getProducts();
        //Εμφανίζουμε όλα τα προϊόντα της βάσης με απόθεμα>0.
        for (Product i: products2){
            int id = i.getId();
            String title = i.getTitle();
            String description =i.getDescription();
            int price = i.getPrice();
            int stack =i.getStack();
            if(stack>0){
                ids.add(id);
                products.add(title +"\nΤιμή: "+price+"\nΠεριγραφή: "+description+"\nΑπομένουν: "+stack);}
        }


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter adapter = new ProductAdapter(products, this);
        recyclerView.setAdapter(adapter);//Αφού έχουμε καταχωρήσει τα προϊόντα, εμφανίζουμε τα
                                         //αντίστοιχα buttons.
        return view;
    }

    public void onMyClickListener(int position) {
        //Όταν πατηθεί κάποιο προϊόν, το προσθέτουμε στο καλάθι.
        try{
            MainActivity.addCart(ids.get(position));
            Toast.makeText(getActivity(),"Προσθέθηκε επιτυχώς!",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getActivity(),"Ούψς! Κάποιο σφάλμα συνέβει και δεν προσθέθηκε η παραγγελία σου.",Toast.LENGTH_SHORT).show();
            Log.e("onSeeProducts",""+e);
        }

    }
}
