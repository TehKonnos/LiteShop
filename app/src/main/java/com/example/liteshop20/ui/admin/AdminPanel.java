package com.example.liteshop20.ui.admin;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.liteshop20.MainActivity;
import com.example.liteshop20.R;

public class AdminPanel extends Fragment{
    private AdminPanelViewModel adminPanelViewModel;
    Button buttonQ1;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //if (container!=null) container.clearDisappearingChildren();
        ViewModelProviders.of(this).get(AdminPanelViewModel.class);
        View root = inflater.inflate(R.layout.admin_panel_fragment, container, false);
        MainActivity.fragmentManager.beginTransaction().replace(R.id.AdminFrame, new AdminTools()).addToBackStack(null).commit();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adminPanelViewModel = ViewModelProviders.of(this).get(AdminPanelViewModel.class);
        // TODO: Use the ViewModel
    }

}
