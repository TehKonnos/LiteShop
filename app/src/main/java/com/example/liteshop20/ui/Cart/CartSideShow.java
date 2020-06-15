package com.example.liteshop20.ui.Cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.liteshop20.MainActivity;
import com.example.liteshop20.R;
import com.example.liteshop20.SeeCart;
import com.example.liteshop20.SeeProducts;

public class CartSideShow extends Fragment {

    private CardSideShowViewModel cardSideShowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cardSideShowViewModel =
                ViewModelProviders.of(this).get(CardSideShowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        MainActivity.fragmentManager.beginTransaction().replace(R.id.showCarHere, new SeeCart()).addToBackStack(null).commit();
        return root;
    }
}
