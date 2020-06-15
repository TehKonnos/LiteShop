package com.example.liteshop20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteProduct extends Fragment {

    public DeleteProduct() {
        // Required empty public constructor
    }
    private EditText eText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete_product, container, false);
        eText=view.findViewById(R.id.pdeleditText7);
        Button buttonPDel=view.findViewById(R.id.buttonPDel);
        buttonPDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Var_id =0;
                try{ //Ελέγχουμε αν το ID είναι σωστό.
                    Var_id=Integer.parseInt(eText.getText().toString());
                }catch(NumberFormatException ex){
                    Toast.makeText(getActivity(),"Could not parse "+ ex,Toast.LENGTH_LONG).show();
                }
                try{ //Δοκιμάζουμε να διαγράψουμε το προϊον με το ID που έδωσε ο χρήστης.
                    Product product = new Product();
                    product.setId(Var_id);
                    MainActivity.myAppDatabase.myDao().deleteProduct(product);
                    Toast.makeText(getActivity(),"Το προϊόν διαγράφηκε επιτυχώς",Toast.LENGTH_LONG).show();
                    eText.setText("");
                }catch(Exception e){
                    Toast.makeText(getActivity(),"Αδυναμία διαγραφής προϊόντος: "+ e,Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }
}
