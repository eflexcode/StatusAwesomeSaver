package com.eflexsoft.statusawesomesaver.repositories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.eflexsoft.statusawesomesaver.model.StatusModel;
import com.eflexsoft.statusawesomesaver.utils.Constance;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhatsappBusinessVideoRepository {

    public MutableLiveData<List<StatusModel>> listMutableLiveData = new MutableLiveData<>();
    List<StatusModel> statusModelList = new ArrayList<>();

    Context context;

    public WhatsappBusinessVideoRepository(Context context) {
        this.context = context;
    }


    public void getVideo(final Handler handler) {

        if (Constance.BusinessWhatsAppDirectory.exists()) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] files = Constance.BusinessWhatsAppDirectory.listFiles();

                    if (files != null && files.length > 0) {

                        Arrays.sort(files);

                        statusModelList.clear();

                        for (File file : files) {

                            StatusModel statusModel = new StatusModel(file.getName(), file.getAbsolutePath(), file);
                            statusModel.setThumb(getThumb(statusModel));

                            if (statusModel.isVideo()) {

                                statusModelList.add(statusModel);

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listMutableLiveData.setValue(statusModelList);

                                    }
                                });
                            }
                        }


                    }

                }
            }).start();


        }else {
            Toast.makeText(context, "no status found", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getThumb(StatusModel statusModel) {

        if (statusModel.isVideo()) {
            return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
        } else {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()), 500, 500);
        }

    }
}
