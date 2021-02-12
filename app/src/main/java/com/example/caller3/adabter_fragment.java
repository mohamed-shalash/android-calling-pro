package com.example.caller3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class adabter_fragment extends FragmentStatePagerAdapter {

    ArrayList<mytabes> tab =new ArrayList<>();
    public void add_tab(mytabes ta){
        tab.add(ta);
    }

    public adabter_fragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tab.get(position).getFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab.get(position).getName();
    }

    @Override
    public int getCount() {
        return tab.size();
    }
}
