package com.example.winwinapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class expenses_Pageadapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentListTitles = new ArrayList<>();

    public expenses_Pageadapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentListTitles.size();
    }
    public CharSequence getPageTitle (int position) {
        return fragmentListTitles.get(position);
    }
    public void AddFragement (Fragment fragment,String Title){
        fragmentList.add(fragment);
        fragmentListTitles.add(Title);
    }
}
