package com.eflexsoft.statusawesomesaver.viewmodels;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eflexsoft.statusawesomesaver.model.StatusModel;
import com.eflexsoft.statusawesomesaver.repositories.WhatsappBusinessImageRepository;
import com.eflexsoft.statusawesomesaver.repositories.WhatsappBusinessVideoRepository;

import java.util.List;

public class WhatsappBusinessImagesViewModel extends AndroidViewModel {

    WhatsappBusinessImageRepository repository;

    public WhatsappBusinessImagesViewModel(@NonNull Application application) {
        super(application);
        repository = new WhatsappBusinessImageRepository(application);

    }

    public LiveData<List<StatusModel>> getStatus(Handler handler){
        repository.getImages(handler);

        return repository.listMutableLiveData;
    }

}
