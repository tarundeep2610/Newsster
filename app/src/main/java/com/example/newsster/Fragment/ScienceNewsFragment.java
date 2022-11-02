package com.example.newsster.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsster.Adapter.RecyclerViewAdapter;
import com.example.newsster.Api.ApiUtils;
import com.example.newsster.Api.DataFetcher;
import com.example.newsster.News;
import com.example.newsster.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScienceNewsFragment extends Fragment {

    private RecyclerView recyclerViewOfScience;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<News> newsList;
    private String country="in";
    public static String apiKey= "d08233df855c4f668ff7dc37599e3e60";
    private String category="science";
    public ScienceNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_science_news, container, false);

        recyclerViewOfScience= view.findViewById(R.id.recyclerViewScience);
        recyclerViewOfScience.setHasFixedSize(true);
        recyclerViewOfScience.setLayoutManager(new LinearLayoutManager(container.getContext()));
        newsList= new ArrayList<>();

        recyclerViewAdapter= new RecyclerViewAdapter(getContext(),newsList,false);
        recyclerViewOfScience.setAdapter(recyclerViewAdapter);
        findNews();
        return view;
    }

    private void findNews(){
        ApiUtils.getApiInterface().getCategoryNews(country,category,100,apiKey).enqueue(new Callback<DataFetcher>() {
            @Override
            public void onResponse(Call<DataFetcher> call, Response<DataFetcher> response) {
                if(response.isSuccessful()){
                    newsList.addAll(response.body().getArticles());
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DataFetcher> call, Throwable t) {

            }
        });
    }
}