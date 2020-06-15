package com.example.liteshop20;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductTools extends Fragment implements View.OnClickListener{

    public ProductTools() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_tools, container, false);
        Button ubutton1,ubutton2,ubutton3,ubutton4;
        //Ετοιμάζουμε τα κουμπιά και τον Listener στο καθένα.
        ubutton1=view.findViewById(R.id.ptAdd);
        ubutton1.setOnClickListener(this);

        ubutton2=view.findViewById(R.id.ptDelete);
        ubutton2.setOnClickListener(this);

        ubutton3=view.findViewById(R.id.ptUpdate);
        ubutton3.setOnClickListener(this);

        ubutton4=view.findViewById(R.id.ptQuery);
        ubutton4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        //Όταν πατηθεί κάποιο κουμπί, ανοίγει το κατάλληλο Fragment
        switch(view.getId()){
            case R.id.ptAdd:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame,new AddProduct()).addToBackStack(null).commit();
                break;
            case R.id.ptDelete:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new DeleteProduct()).addToBackStack(null).commit();
                break;
            case R.id.ptUpdate:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new UpdateProduct()).addToBackStack(null).commit();
                break;
            case R.id.ptQuery:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new QueryProduct()).addToBackStack(null).commit();
                break;
        }
    }
}
