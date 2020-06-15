package com.example.liteshop20.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.liteshop20.AddUser;
import com.example.liteshop20.DeleteUser;
import com.example.liteshop20.MainActivity;
import com.example.liteshop20.QueryUser;
import com.example.liteshop20.R;
import com.example.liteshop20.UpdateUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserTools extends Fragment implements View.OnClickListener{

    public UserTools() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_tools, container, false);
        Button ubutton1,ubutton2,ubutton3,ubutton4;

        ubutton1=view.findViewById(R.id.utAdd);
        ubutton1.setOnClickListener(this);

        ubutton2=view.findViewById(R.id.utDelete);
        ubutton2.setOnClickListener(this);

        ubutton3=view.findViewById(R.id.utUpdate);
        ubutton3.setOnClickListener(this);

        ubutton4=view.findViewById(R.id.utQuery);
        ubutton4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.utAdd:
               // Intent openA1 = new Intent(getActivity(), UserAddActivity.class);
              //  startActivity(openA1);
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new AddUser()).addToBackStack(null).commit();
                break;
            case R.id.utDelete:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new DeleteUser()).addToBackStack(null).commit();
                break;
            case R.id.utUpdate:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new UpdateUser()).addToBackStack(null).commit();
                break;
            case R.id.utQuery:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new QueryUser()).addToBackStack(null).commit();
                break;
        }
    }
}
