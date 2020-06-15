package com.example.liteshop20.ui.Products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.liteshop20.MainActivity;
import com.example.liteshop20.R;
import com.example.liteshop20.SeeProducts;

public class ProductSideShow extends Fragment {

    private ProductSideShowViewModel productSideShowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productSideShowViewModel =
                ViewModelProviders.of(this).get(ProductSideShowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_product_side_show, container, false);
        MainActivity.fragmentManager.beginTransaction().replace(R.id.showProductsHere, new SeeProducts()).addToBackStack(null).commit();

        return root;
    }
}
