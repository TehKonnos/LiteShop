package com.example.liteshop20;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QueryUser extends Fragment {
    public QueryUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container!=null) container.clearDisappearingChildren();
        View view=inflater.inflate(R.layout.fragment_query, container, false);
        TextView textView = view.findViewById(R.id.txtquery);
        List<User> users =MainActivity.myAppDatabase.myDao().getUsers();
        StringBuilder result = new StringBuilder("Οι χρήστες στη βάση είναι: \n ----------------------- \n");
        for (User i: users){ //Αποθηκεύουμε ένα ένα τα αποτελέσματα και μετά τα εμφανίζουμε χρησιμοποιόντας το textView
            int id = i.getId();
            String name = i.getName();
            String address =i.getAddress();
            String town = i.getTown();
            String phone =i.getPhoneNum();
            String postCode =i.getPostCode();
            String notes=i.getNotes();
            result.append("\n ID: ").append(id).append("\n Όνομα: ").append(name).append("\n Διεύθυνση: ").append(address).append("\n Πόλη: ").append(town).append("\n Ταχυδρομικός Κώδικας: ").append(postCode).append("\n Τηλέφωνο: ").append(phone).append("\n Σχόλια: ").append(notes).append("\n --------------------------\n");

        }
       textView.setText(result.toString());
        return view;
    }
}
