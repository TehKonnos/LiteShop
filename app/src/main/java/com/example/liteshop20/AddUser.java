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
public class AddUser extends Fragment implements View.OnClickListener{
    private EditText editT1,editT2,editT3,editT4,editT5,editT6;

    public AddUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_user, container, false);

        //Το κουμπί που περιμένουμε να πατηθεί
        Button button = view.findViewById(R.id.buttonfr);
        button.setOnClickListener(this);
        //Πέρνουμε τις τιμές που δίνει ο χρήστης
        editT1 = view.findViewById(R.id.editTextfr);
        editT2 = view.findViewById(R.id.editText2fr);
        editT3 = view.findViewById(R.id.editText3fr);
        editT4 = view.findViewById(R.id.editText4fr);
        editT5 = view.findViewById(R.id.editText5fr);
        editT6 = view.findViewById(R.id.editText6fr);

        return view;
    }

    @Override
    public void onClick(View view) {
        String Var_phoneNum;
        boolean flag=true;
        //Χρειάζεται έλεγχος για τις εισόδους
        String Var_username = editT1.getText().toString();
        String Var_address = editT2.getText().toString();
        String Var_postCode =editT3.getText().toString();
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
        String Var_notes =""+editT6.getText().toString();
        if(flag) {
            User user = new User(); //Δίνουμε "μορφή" στα δεδομένα για να τα εισάγουμε στη βάση.
            //user.setId(0); //Έχω θέση το id σε auto-generate
            user.setName(Var_username);
            user.setAddress(Var_address);
            user.setPostCode(Var_postCode);
            user.setPhoneNum(Var_phoneNum);
            user.setNotes(Var_notes);
            user.setTown(Var_town);
            MainActivity.myAppDatabase.myDao().addUser(user); //Κάνουμε insert τις τιμές που πήραμε στη βάση.
            Toast.makeText(getActivity(), "Έγινε!", Toast.LENGTH_LONG).show(); //Μήνυμα πως όλα πήγαν καλά.
            editT1.setText("");
            editT2.setText("");
            editT3.setText("");
            editT4.setText("");
            editT5.setText("");
            editT6.setText("");
        }
    }
}

