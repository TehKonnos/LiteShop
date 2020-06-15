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
public class UpdateUser extends Fragment{
    private EditText editT1,editT2,editT3,editT4,editT5,editT6;
    public UpdateUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update_user, container, false);

        Button updateb = view.findViewById(R.id.upbutton);
        updateb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Πέρνουμε τις τιμές που δίνει ο χρήστης
                editT1 = view.findViewById(R.id.upeditText);
                editT2 = view.findViewById(R.id.upeditText2);
                editT3 = view.findViewById(R.id.upeditText3);
                editT4 = view.findViewById(R.id.upeditText4);
                editT5 = view.findViewById(R.id.upeditText5);
                editT6 = view.findViewById(R.id.upeditText6); //Αυτο στο AddUser ηταν το Note, αλλά τωρα το έχουμε για το ID.
                String Var_phoneNum;
                int Var_id=0;
                boolean flag=true;
                //Χρειάζεται έλεγχος για τις εισόδους
                try{
                    Var_id =Integer.parseInt(editT6.getText().toString());
                }catch(Exception e){
                    Toast.makeText(getActivity(),"Σφάλμα: "+e,Toast.LENGTH_LONG).show();
                }
                String Var_username = editT1.getText().toString();
                String Var_address = editT2.getText().toString();
                String Var_postCode=editT3.getText().toString();
                if(Var_postCode.length()!=5){ //Έλεγχος ταχυδρομικού κώδικα
                    flag=false;
                    Toast.makeText(getActivity(),"Ο ταχυδρομικός κώδικας δεν είναι σωστός",Toast.LENGTH_LONG).show();
                }
                String Var_town = editT4.getText().toString();
                Var_phoneNum =editT5.getText().toString();
                if (Var_phoneNum.length()!=10 && flag){ //Έλεγχος τηλεφώνου, και αν ειναι ήδη το flag=false τοτε δεν μπαινει γτ το ένα Toast θα σβήσει το προηγούμενο.
                    flag=false;
                    Toast.makeText(getActivity(),"Ο τηλεφωνικός αριθμός που έδωσες δεν είναι σωστός", Toast.LENGTH_LONG).show();
                }
                if(flag) {
                    try {
                        User user = new User(); //Δίνουμε "μορφή" στα δεδομένα για να τα εισάγουμε στη βάση.
                        user.setId(Var_id);
                        user.setName(Var_username);
                        user.setAddress(Var_address);
                        user.setPostCode(Var_postCode);
                        user.setPhoneNum(Var_phoneNum);
                        user.setTown(Var_town);
                        MainActivity.myAppDatabase.myDao().updateUser(user); //Κάνουμε update Τα στοιχεία του User
                        Toast.makeText(getActivity(), "Έγινε!", Toast.LENGTH_LONG).show(); //Μήνυμα πως όλα πήγαν καλά.
                        editT1.setText("");
                        editT2.setText("");
                        editT3.setText("");
                        editT4.setText("");
                        editT5.setText("");
                        editT6.setText("");
                    }catch(Exception e){
                        Toast.makeText(getActivity(),"Σφάλμα: "+e,Toast.LENGTH_LONG).show();
                    }
                }

                }

        });

        return view;
    }
}
