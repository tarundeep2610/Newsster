package com.example.newsster.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsster.Fragment.BusinessNewsFragment;
import com.example.newsster.Fragment.HealthNewsFragment;
import com.example.newsster.Fragment.SavedFragment;
import com.example.newsster.Fragment.ScienceNewsFragment;
import com.example.newsster.Fragment.SportsNewsFragment;
import com.example.newsster.Fragment.HomeNewsFragment;
import com.example.newsster.Fragment.TechnologyNewsFragment;
import com.example.newsster.SearchActivity;

public class ViewPagerNewsAdapter extends FragmentPagerAdapter {
    public ViewPagerNewsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new HomeNewsFragment();
        }
        else if(position==1){
            return new SportsNewsFragment();
        }
        else if(position==2){
            return new BusinessNewsFragment();
        }
        else if(position==3){
            return new ScienceNewsFragment();
        }
        else if(position==4){
            return new TechnologyNewsFragment();
        }

        else if(position==5){
            return new HealthNewsFragment();
        }
        else{
            return new SavedFragment();
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Home";
        }
        else if(position==1){
            return "Sports";
        }
        else if(position==2){
            return "Business";
        }
        else if(position==3){
            return "Science";
        }
        else if (position==4){
            return "Technology";
        }
        else if(position==5){
            return "Health";
        }
        else{
            return "Saved";
        }
    }
}
