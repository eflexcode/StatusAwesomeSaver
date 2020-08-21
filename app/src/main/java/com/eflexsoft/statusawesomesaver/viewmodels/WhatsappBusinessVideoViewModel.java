package com.eflexsoft.statusawesomesaver.viewmodels;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eflexsoft.statusawesomesaver.model.StatusModel;
import com.eflexsoft.statusawesomesaver.repositories.WhatsappBusinessVideoRepository;
import com.eflexsoft.statusawesomesaver.repositories.WhatsappVideoRepository;

import java.util.List;

public class WhatsappBusinessVideoViewModel extends AndroidViewModel {

    WhatsappBusinessVideoRepository repository;

    public WhatsappBusinessVideoViewModel(@NonNull Application application) {
        super(application);
        repository = new WhatsappBusinessVideoRepository(application);

    }

    public LiveData<List<StatusModel>> getStatus(Handler handler){
        repository.getVideo(handler);

        return repository.listMutableLiveData;
    }

}
