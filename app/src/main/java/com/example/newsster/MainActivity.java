package com.example.newsster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsster.Adapter.ViewPagerNewsAdapter;
import com.example.newsster.database.NewsDbHandler;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView searchButton;
    public ViewPagerNewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout= findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager= findViewById(R.id.viewPager);

        adapter= new ViewPagerNewsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        //Up until here we just created our tab and viewpager in activity_main
        //then we made our adapter for viewpager to handle which activity to be shown at which tab
        //then above we created tablayout and viewpager variables and add the adapter to viewpager
        //for the tab change while scrolling the fragment it is done by the code in 24th line

        searchButton= findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

    }

}