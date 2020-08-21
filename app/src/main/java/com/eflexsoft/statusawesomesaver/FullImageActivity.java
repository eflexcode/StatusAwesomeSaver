package com.eflexsoft.statusawesomesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.eflexsoft.statusawesomesaver.model.StatusModel;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_full_image);

        PhotoView imageView = findViewById(R.id.fullImage);

        String path = getIntent().getStringExtra("path");

        imageView.setImageBitmap(getThumb(path));
    }

    private Bitmap getThumb(String path) {

        return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path), 1000, 1000);
    }

}

