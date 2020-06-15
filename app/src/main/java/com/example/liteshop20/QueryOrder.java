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
public class QueryOrder extends Fragment {

    public QueryOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_query_order, container, false);

        TextView textView = view.findViewById(R.id.oQtextView);
        List<Orders> orders =MainActivity.myAppDatabase.myDao().getOrders();
        StringBuilder result = new StringBuilder("Οι παραγγελίες στη βάση είναι: \n ----------------------- \n");
        for (Orders i: orders){ //Αποθηκεύουμε ένα ένα τα αποτελέσματα και μετά τα εμφανίζουμε χρησιμοποιόντας το textView
            int UserID = i.getUid();
            String date =i.getOr_date();
            int ProdID = i.getPid();
            int amount =i.getOr_Quantity();

            result.append("\n ID Χρήστη: ").append(UserID).append("\n ID Προϊόντος: ").append(ProdID).append("\n Ποσότητα ").append(amount).append("\n Ημ/νία: ").append(date).append("\n --------------------------\n");

        }
        textView.setText(result.toString());


        return view;
    }
}
