package com.eflexsoft.statusawesomesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideoActivity extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_vdeo_acitivity);

        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        String path = getIntent().getStringExtra("path");


        videoView.setVideoPath(path);
        videoView.setMediaController(mediaController);
        videoView.start();

    }
}