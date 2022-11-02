package com.example.newsster.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsster.Adapter.RecyclerViewAdapter;
import com.example.newsster.MainActivity;
import com.example.newsster.News;
import com.example.newsster.R;
import com.example.newsster.database.NewsDbHandler;

import java.util.ArrayList;


public class SavedFragment extends Fragment {

    private RecyclerView recyclerViewOfSaved;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<News> newsList;
    private TextView textMsg;
    private  NewsDbHandler dbHandler;
    public SavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saved, container, false);
        recyclerViewOfSaved= view.findViewById(R.id.recyclerViewSaved);
        textMsg= view.findViewById(R.id.emptyText);
        recyclerViewOfSaved.setHasFixedSize(true);
        recyclerViewOfSaved.setLayoutManager(new LinearLayoutManager(container.getContext()));
        newsList= new ArrayList<>();

        dbHandler= new NewsDbHandler(getContext());
        newsList= dbHandler.getAllSavedNews();

        recyclerViewAdapter= new RecyclerViewAdapter(getContext(),newsList,true);
        recyclerViewOfSaved.setAdapter(recyclerViewAdapter);
        if(newsList.isEmpty() || newsList.get(0).getTitle()==null){
            recyclerViewOfSaved.setVisibility(View.GONE);
            textMsg.setVisibility(View.VISIBLE);
        }
        else{
            recyclerViewOfSaved.setVisibility(View.VISIBLE);
            textMsg.setVisibility(View.GONE);
        }

        Log.v("newster data",newsList.size()+"");

        for(News news: newsList){
            Log.v("newster data",news.getId()+" "+news.getTitle()+" "+news.getDescription()+" "+news.getAuthor()+" "+news.getPublishedAt());
        }
        recyclerViewAdapter.notifyDataSetChanged();
        return view;
    }


}