package com.example.liteshop20.ui.Cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CardSideShowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CardSideShowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Ακόμη δεν υπαρχει τίποτα εδώ (Καλάθι)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}