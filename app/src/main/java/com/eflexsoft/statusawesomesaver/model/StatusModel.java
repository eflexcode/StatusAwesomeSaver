package com.eflexsoft.statusawesomesaver.model;

import android.graphics.Bitmap;

import java.io.File;

public class StatusModel {


    String mp4 = "mp4";
   private String title;
   private String path;
   private Bitmap thumb;
   private File file;
   private boolean isVideo;

    public StatusModel(String title, String path, File file) {
        this.title = title;
        this.path = path;
        this.file = file;
        isVideo = file.getName().endsWith(mp4);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }
}
