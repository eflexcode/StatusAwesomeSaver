package com.eflexsoft.statusawesomesaver.viewmodels;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eflexsoft.statusawesomesaver.model.StatusModel;
import com.eflexsoft.statusawesomesaver.repositories.WhatsappVideoRepository;

import java.util.List;

public class WhatsappVideoViewModel extends AndroidViewModel {

    WhatsappVideoRepository repository;

    public WhatsappVideoViewModel(@NonNull Application application) {
        super(application);
        repository = new WhatsappVideoRepository(application);

    }

    public LiveData<List<StatusModel>> getStatus(Handler handler){
        repository.getVideos(handler);


        return repository.listMutableLiveData;
    }

}
