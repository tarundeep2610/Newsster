package com.example.newsster.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsster.Adapter.RecyclerViewAdapter;
import com.example.newsster.Api.ApiInterface;
import com.example.newsster.Api.ApiUtils;
import com.example.newsster.Api.DataFetcher;
import com.example.newsster.News;
import com.example.newsster.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeNewsFragment extends Fragment {


    private RecyclerView recyclerViewOfHome;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<News> newsList;
    private String country="in";
     public static String apiKey= "d08233df855c4f668ff7dc37599e3e60";

    public HomeNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_home_news, container, false);
        recyclerViewOfHome= view.findViewById(R.id.recyclerViewHome);
        recyclerViewOfHome.setHasFixedSize(true);
        recyclerViewOfHome.setLayoutManager(new LinearLayoutManager(container.getContext()));
        newsList= new ArrayList<>();

        recyclerViewAdapter= new RecyclerViewAdapter(getContext(),newsList,false);
        recyclerViewOfHome.setAdapter(recyclerViewAdapter);
        findNews();
        return view;
    }

    private void findNews(){
        ApiUtils.getApiInterface().getNews(country,100,apiKey).enqueue(new Callback<DataFetcher>() {
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