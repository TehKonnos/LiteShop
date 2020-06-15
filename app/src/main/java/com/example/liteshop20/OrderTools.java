package com.example.liteshop20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderTools extends Fragment implements View.OnClickListener{

    public OrderTools() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order_tools, container, false);

        //Ετοιμάζουμε τα κουμπιά και τον Listener στο καθένα.
        Button ubutton1,ubutton2,ubutton3,ubutton4;

        ubutton1=view.findViewById(R.id.otAdd);
        ubutton1.setOnClickListener(this);

        ubutton2=view.findViewById(R.id.otDelete);
        ubutton2.setOnClickListener(this);

        ubutton3=view.findViewById(R.id.otUpdate);
        ubutton3.setOnClickListener(this);

        ubutton4=view.findViewById(R.id.otQuery);
        ubutton4.setOnClickListener(this);

        return view;
    }
    //Όταν πατηθεί κάποιο κουμπί, ανοίγει το κατάλληλο Fragment
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.otAdd:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame,new AddOrder()).addToBackStack(null).commit();
                break;
            case R.id.otDelete:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new DeleteOrder()).addToBackStack(null).commit();
                break;
            case R.id.otUpdate:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new UpdateOrder()).addToBackStack(null).commit();
                break;
            case R.id.otQuery:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new QueryOrder()).addToBackStack(null).commit();
                break;
        }
    }
}
