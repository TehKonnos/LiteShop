package com.example.liteshop20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProduct extends Fragment {

    private EditText eT1,eT2,eT3,eT4,eT0;
    public UpdateProduct() {
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
        View view= inflater.inflate(R.layout.fragment_update_product, container, false);
        //Αποθήκευση των τιμών που έδωσε ο χρήστης
        eT0=view.findViewById(R.id.pupdeditText20);
        eT1=view.findViewById(R.id.pupdeditText8);
        eT2=view.findViewById(R.id.pupdeditText9);
        eT3=view.findViewById(R.id.pupdeditText10);
        eT4=view.findViewById(R.id.pupdeditText11);
        //Οταν ο χρήστης πατήσει το κουμπί, τότε ξεκινάει η διαδικασία για να αποθηκευτούν τα δεδομένα στη βάση.
        Button addBtn =view.findViewById(R.id.buttonpupd);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int Var_price=0; //Τιμή προϊόντος
                int Var_stack=0; //Απόθεμα
                int Var_id=0;//ID προϊόντος
                try{
                    Var_id=Integer.parseInt(eT0.getText().toString());
                }catch(Exception ex){
                    Toast.makeText(getActivity(),"Μή αποδεκτή τιμή ID",Toast.LENGTH_LONG).show();
                }
                String Var_title = eT1.getText().toString();
                String Var_desc =eT2.getText().toString();
                try{ //Ελέγχουμε άν έχουμε σωστές τιμές
                    Var_price = Integer.parseInt(eT3.getText().toString());
                }catch (Exception ex){
                    Toast.makeText(getActivity(),"Μή αποδεκτή τιμή",Toast.LENGTH_LONG).show();
                }
                try{ //Ελέγχουμε άν έχουμε σωστές τιμές
                    Var_stack = Integer.parseInt(eT4.getText().toString());
                }catch (Exception ex){
                    Toast.makeText(getActivity(),"Μή αποδεκτή τιμή αποθέματος",Toast.LENGTH_LONG).show();
                }
                try { //Κάνουμε insert τις τιμές στη βάση.
                    Product product = new Product();
                    product.setId(Var_id);
                    product.setTitle(Var_title);
                    product.setDescription(Var_desc);
                    product.setPrice(Var_price);
                    product.setStack(Var_stack);
                    MainActivity.myAppDatabase.myDao().updateProduct(product); //Εισάγουμε τα δεδομένα στη βάση.
                    Toast.makeText(getActivity(),"Ολοκλήρωση! Το προϊόν ανανεώθηκε.",Toast.LENGTH_LONG).show();
                    eT0.setText("");
                    eT1.setText("");
                    eT2.setText("");
                    eT3.setText("");
                    eT4.setText("");
                }catch(Exception e){ //Αν κάτι δεν πήγε σωστά, εμφανίζουμε μήνυμα προτροπής στον χρήστη.
                    Toast.makeText(getActivity(),"Σφάλμα! Το προϊόν δεν αποθηκεύτηκε.",Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }
}
