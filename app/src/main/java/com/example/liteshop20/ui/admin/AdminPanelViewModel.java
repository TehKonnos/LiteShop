package com.example.liteshop20.ui.admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminPanelViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AdminPanelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Admin's Panel fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
