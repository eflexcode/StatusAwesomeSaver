package com.eflexsoft.statusawesomesaver.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eflexsoft.statusawesomesaver.R;
import com.eflexsoft.statusawesomesaver.adapter.WhatAppThumbNailAdapter;
import com.eflexsoft.statusawesomesaver.model.StatusModel;
import com.eflexsoft.statusawesomesaver.utils.Constance;
import com.eflexsoft.statusawesomesaver.viewmodels.WhatsappImageViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhatsAppImageFragment extends Fragment {

    WhatsappImageViewModel viewModel;
    RecyclerView recyclerView;
    WhatAppThumbNailAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_whats_app_image, container, false);
        handler = new Handler();
        recyclerView = view.findViewById(R.id.imageRecycleView);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setRefreshing(true);
        viewModel = ViewModelProviders.of(this).get(WhatsappImageViewModel.class);


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setVisibility(View.VISIBLE);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },4000);
                getStatus();
            }
        });

        recyclerView.setVisibility(View.GONE);
        getStatus();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        },4000);






        return view;
    }

    private void getStatus() {
        Handler handler = new Handler();

        viewModel.getStatus(handler).observe(getViewLifecycleOwner(), new Observer<List<StatusModel>>() {
            @Override
            public void onChanged(List<StatusModel> statusModels) {
                adapter = new WhatAppThumbNailAdapter(statusModels,getContext());
                recyclerView.setAdapter(adapter);
            }
        });
    }


}