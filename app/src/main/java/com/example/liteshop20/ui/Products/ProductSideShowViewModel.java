package com.example.liteshop20.ui.Products;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductSideShowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProductSideShowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Ακόμη δεν υπαρχει τίποτα εδώ (Products)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}