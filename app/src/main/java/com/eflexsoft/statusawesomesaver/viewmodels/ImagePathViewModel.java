package com.eflexsoft.statusawesomesaver.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ImagePathViewModel extends AndroidViewModel {

    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public ImagePathViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> stringLiveData(){
        return stringMutableLiveData;
    }

}
