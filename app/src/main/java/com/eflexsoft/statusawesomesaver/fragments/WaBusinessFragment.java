package com.eflexsoft.statusawesomesaver.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.eflexsoft.statusawesomesaver.R;
import com.eflexsoft.statusawesomesaver.adapter.WhatsAppAdapter;
import com.google.android.material.tabs.TabLayout;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.nativead.StartAppNativeAd;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class WaBusinessFragment extends Fragment {


    WhatsAppAdapter appAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container1,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_whats_business_app, container1, false);



        tabLayout = view.findViewById(R.id.tabLayout_whatsAppBusiness);
        viewPager = view.findViewById(R.id.viewPager_whatsAppBusiness);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new WhatsAppBusinessImageFragment());
        fragments.add(new whatsAppBusinessViedoFragment());

        List<String> strings = new ArrayList<>();
        strings.add("Images");
        strings.add("Videos");

        appAdapter = new WhatsAppAdapter(getChildFragmentManager(),fragments,strings);

        viewPager.setAdapter(appAdapter);

        tabLayout.setupWithViewPager(viewPager);




//        StartAppAd.showAd(getContext());
        return view;
    }
}