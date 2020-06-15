package com.example.liteshop20.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.liteshop20.MainActivity;
import com.example.liteshop20.OrderTools;
import com.example.liteshop20.ProductTools;
import com.example.liteshop20.R;
import com.example.liteshop20.Stats;
import com.example.liteshop20.ui.UserTools;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminTools extends Fragment implements View.OnClickListener{
    public AdminTools() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_tools, container, false);
        Button bUsers,bProducts,bOrders,bStats;

        bUsers=view.findViewById(R.id.atbusers);
        bUsers.setOnClickListener(this);

        bProducts = view.findViewById(R.id.atbproducts);
        bProducts.setOnClickListener(this);

        bOrders = view.findViewById(R.id.atbOrders);
        bOrders.setOnClickListener(this);

        bStats= view.findViewById(R.id.atbStats);
        bStats.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.atbusers:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new UserTools()).addToBackStack(null).commit();
                break;
            case R.id.atbOrders:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new OrderTools()).addToBackStack(null).commit();
                break;
            case R.id.atbproducts:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new ProductTools()).addToBackStack(null).commit();
                break;
            case R.id.atbStats:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new Stats()).addToBackStack(null).commit();
                break;
        }
    }
}
