package com.eflexsoft.statusawesomesaver.viewmodels;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eflexsoft.statusawesomesaver.model.StatusModel;
import com.eflexsoft.statusawesomesaver.repositories.WhatsappImageRepository;

import java.util.List;

public class WhatsappImageViewModel extends AndroidViewModel {

    WhatsappImageRepository repository;

    public WhatsappImageViewModel(@NonNull Application application) {
        super(application);
        repository = new WhatsappImageRepository(application);

    }

    public LiveData<List<StatusModel>> getStatus(Handler handler){
        repository.getImages(handler);

        return repository.listMutableLiveData;
    }

}
