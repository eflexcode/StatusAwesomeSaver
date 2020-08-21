package com.eflexsoft.statusawesomesaver.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class WhatsAppAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    List<String> stringList;

    public WhatsAppAdapter(@NonNull FragmentManager fm,List<Fragment> fragments,List<String> strings) {
        super(fm);
        this.fragmentList = fragments;
        this.stringList = strings;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }
}
