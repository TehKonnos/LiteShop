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
public class DeleteOrder extends Fragment {
    private EditText eText1,eText2;
    public DeleteOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete_order, container, false);

        eText1=view.findViewById(R.id.editText8oDel);
        eText2=view.findViewById(R.id.editText9oDel);
        Button buttonPDel=view.findViewById(R.id.buttonpDel);
        buttonPDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Var_uid =0;
                int Var_pid=0;
                try{ //Ελέγχουμε αν το User ID είναι σωστό.
                    Var_uid=Integer.parseInt(eText1.getText().toString());
                }catch(NumberFormatException ex){
                    Toast.makeText(getActivity(),"Could not parse "+ ex,Toast.LENGTH_LONG).show();
                }

                try{ //Ελέγχουμε αν το Product ID είναι σωστό.
                    Var_pid=Integer.parseInt(eText2.getText().toString());
                }catch(NumberFormatException ex){
                    Toast.makeText(getActivity(),"Could not parse "+ ex,Toast.LENGTH_LONG).show();
                }

                try{ //Δοκιμάζουμε να διαγράψουμε το προϊον με το ID που έδωσε ο χρήστης.
                    Orders orders = new Orders();
                    orders.setUid(Var_uid);
                    orders.setPid(Var_pid);
                    MainActivity.myAppDatabase.myDao().deleteOrder(Var_uid,Var_pid);
                    Toast.makeText(getActivity(),"Το προϊόν διαγράφηκε επιτυχώς",Toast.LENGTH_LONG).show();
                    eText1.setText("");
                    eText2.setText("");
                }catch(Exception e){
                    Toast.makeText(getActivity(),"Αδυναμία διαγραφής προϊόντος: "+ e,Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
