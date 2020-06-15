package com.example.liteshop20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProduct extends Fragment {
    private EditText eT1,eT2,eT3,eT4;

    public AddProduct() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_product, container, false);
        //Αποθήκευση των τιμών που έδωσε ο χρήστης
        eT1=view.findViewById(R.id.paddeditText8);
        eT2=view.findViewById(R.id.paddeditText9);
        eT3=view.findViewById(R.id.paddeditText10);
        eT4=view.findViewById(R.id.paddeditText11);
        //Οταν ο χρήστης πατήσει το κουμπί, τότε ξεκινάει η διαδικασία για να αποθηκευτούν τα δεδομένα στη βάση.
        Button addBtn =view.findViewById(R.id.buttonpadd);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int Var_price=0;
                int Var_stack=0;
                String Var_title = eT1.getText().toString();
                String Var_desc =eT2.getText().toString();
                try{ //Ελέγχουμε άν έχουμε σωστές τιμές
                    Var_price = Integer.parseInt(eT3.getText().toString());
                }catch (NumberFormatException ex){
                    Toast.makeText(getActivity(),"Μή αποδεκτή τιμή",Toast.LENGTH_LONG).show();
                }
                try{ //Ελέγχουμε άν έχουμε σωστές τιμές
                    Var_stack = Integer.parseInt(eT4.getText().toString());
                }catch (NumberFormatException ex){
                    Toast.makeText(getActivity(),"Μή αποδεκτή τιμή αποθέματος",Toast.LENGTH_LONG).show();
                }
                try { //Κάνουμε insert τις τιμές στη βάση.
                    Product product = new Product();
                    product.setTitle(Var_title);
                    product.setDescription(Var_desc);
                    product.setPrice(Var_price);
                    product.setStack(Var_stack);
                    MainActivity.myAppDatabase.myDao().addProduct(product); //Εισάγουμε τα δεδομένα στη βάση.
                    Toast.makeText(getActivity(),"Ολοκλήρωση! Το προϊόν προσθέθηκε.",Toast.LENGTH_LONG).show();
                    eT1.setText("");
                    eT2.setText("");
                    eT3.setText("");
                    eT4.setText("");
                }catch(Exception e){ //Αν κάτι δεν πήγε σωστά, εμφανίζουμε μήνυμα προτροπής στον χρήστη.
                    Toast.makeText(getActivity(),"Σφάλμα! Το προϊόν δεν αποθηκεύτηκε.",Toast.LENGTH_LONG).show();
                    Log.e("AddProduct",""+e);
                }

            }
        });

        return view;
    }
}
