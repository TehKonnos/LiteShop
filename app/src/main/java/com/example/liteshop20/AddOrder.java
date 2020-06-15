package com.example.liteshop20;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddOrder extends Fragment{
    private EditText text1,text2,text3;
    public AddOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_order, container, false);
        //Διαβάζω τα πεδία
        text1=view.findViewById(R.id.oaddeditText8);
        text2=view.findViewById(R.id.oaddeditText9);
        text3=view.findViewById(R.id.oaddeditText10);
        //Ελέγχω αν πατήθηκε το κουμπί,διαβάζω τις μεταβλητές και προσθέτω στη βάση την παραγγελία.
        Button button1= view.findViewById(R.id.buttonoadd);
        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int Var_uid=0;
                int Var_pid=0;
                int Var_amount=0;
                try{
                    Var_uid=Integer.parseInt(text1.getText().toString());
                }catch(NumberFormatException ex){
                    Toast.makeText(getActivity(),"Λάθος UserID",Toast.LENGTH_LONG).show();
                }
                try{
                    Var_pid=Integer.parseInt(text2.getText().toString());
                }catch(NumberFormatException ex){
                    Toast.makeText(getActivity(),"Λάθος ProductID",Toast.LENGTH_LONG).show();
                }
                try{
                    Var_amount=Integer.parseInt(text3.getText().toString());
                }catch(NumberFormatException ex){
                    Toast.makeText(getActivity(),"Λάθος Ποσότητα",Toast.LENGTH_LONG).show();
                }
                //Πέρνω την ημερομηνία απ το σύστημα
                String Var_date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                try{
                    Orders order = new Orders();
                    order.setUid(Var_uid);
                    order.setPid(Var_pid);
                    order.setOr_Quantity(Var_amount);
                    order.setOr_date(Var_date);
                    MainActivity.myAppDatabase.myDao().addOrder(order);
                    Toast.makeText(getActivity(),"Η παραγγελία προσθέθηκε",Toast.LENGTH_LONG).show();
                    text1.setText("");
                    text2.setText("");
                    text3.setText("");
                }catch (Exception e){
                    Toast.makeText(getActivity(),"Σφάλμα: "+e,Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
