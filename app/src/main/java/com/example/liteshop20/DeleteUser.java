package com.example.liteshop20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUser extends Fragment{

    private EditText text;

    public DeleteUser() {
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
        View view= inflater.inflate(R.layout.fragment_delete_user, container, false);
        text=view.findViewById(R.id.editText7);
        //Ελέγχω αν πατήθηκε το κουμπί, διαβάζω τις μεταβλητές και στέλνω την κατάλληλη εντολή για διαγραφή στη βάση.
        Button deleteB = view.findViewById(R.id.buttonDel);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Var_userid=0;
                try{
                    Var_userid = Integer.parseInt(text.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                try {
                    User user = new User();
                    user.setId(Var_userid);
                    MainActivity.myAppDatabase.myDao().deleteUser(user);
                    Toast.makeText(getActivity(), "Επιτυχής διαγραφή!", Toast.LENGTH_LONG).show();
                    text.setText("");
                }catch(Exception e){
                    Toast.makeText(getActivity(), "Αποτυχία διαγραφής: "+e, Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

}
