package com.eflexsoft.statusawesomesaver.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.eflexsoft.statusawesomesaver.FullImageActivity;
import com.eflexsoft.statusawesomesaver.PlayVideoActivity;
import com.eflexsoft.statusawesomesaver.R;
import com.eflexsoft.statusawesomesaver.model.StatusModel;
import com.eflexsoft.statusawesomesaver.utils.Constance;
import com.eflexsoft.statusawesomesaver.viewmodels.ImagePathViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.List;

public class WhatAppThumbNailAdapter extends RecyclerView.Adapter<WhatAppThumbNailAdapter.Holder> {


    List<StatusModel> statusModelList;

    Context context;

    public WhatAppThumbNailAdapter(List<StatusModel> statusModelList, Context context) {
        this.statusModelList = statusModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.thumb_nail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        final StatusModel statusModel = statusModelList.get(position);
        holder.thumbimage.setImageBitmap(statusModel.getThumb());
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    savePicture(statusModel);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        if (statusModel.isVideo()){
            holder.play.setVisibility(View.VISIBLE);
        }else {
            holder.play.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return statusModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView thumbimage;
        FloatingActionButton save;
        ImageView play;

        public Holder(@NonNull View itemView) {
            super(itemView);

            thumbimage = itemView.findViewById(R.id.imagethumb);
            save = itemView.findViewById(R.id.saveBtn);
            play = itemView.findViewById(R.id.play);

            thumbimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImagePathViewModel viewModel = ViewModelProviders.of((FragmentActivity) context).get(ImagePathViewModel.class);

                    StatusModel statusModel = statusModelList.get(getAdapterPosition());
                    if (!statusModel.isVideo()){
                        Intent intent = new Intent(context, FullImageActivity.class);
                        intent.putExtra("path", statusModel.getFile().getAbsolutePath());
//                        viewModel.stringMutableLiveData.setValue(statusModel.getFile().getAbsoluteFile());
                        context.startActivity(intent);
                    }else {
                        Intent intent = new Intent(context, PlayVideoActivity.class);
                        intent.putExtra("path", statusModel.getFile().getAbsolutePath());
                        context.startActivity(intent);
                    }
                }
            });

        }
    }

    private void savePicture(StatusModel statusModel) throws IOException {

        if (!Constance.MyDirectory.exists()) {
            Constance.MyDirectory.mkdir();
        }

        File desFile = new File(Constance.MyDirectory + File.separator + statusModel.getTitle());

        File fromFile = statusModel.getFile();

        if (desFile.exists()) {
            desFile.delete();
        }

        if (desFile.getParentFile().exists()){
            desFile.getParentFile().mkdir();
        }

        if (!desFile.exists()) {
            desFile.createNewFile();
        }

        FileChannel fromChannel;
        FileChannel toChannel;

        fromChannel = new FileInputStream(statusModel.getFile()).getChannel();
        toChannel = new FileOutputStream(desFile).getChannel();

        toChannel.transferFrom(fromChannel, 0, fromChannel.size());

        toChannel.close();
        fromChannel.close();

        Toast.makeText(context, " saved ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(desFile));
        context.sendBroadcast(intent);
    }

}
