package com.example.newsster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newsster.Adapter.RecyclerViewAdapter;
import com.example.newsster.Api.ApiUtils;
import com.example.newsster.Api.DataFetcher;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private EditText searchKeyword;
    private ImageView imageView;
    private String country="in";
    public static String apiKey= "d08233df855c4f668ff7dc37599e3e60";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<News> newsList;
    private String keyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        newsList= new ArrayList<>();

        recyclerView= findViewById(R.id.searchRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter= new RecyclerViewAdapter(this,newsList,false);
        recyclerView.setAdapter(recyclerViewAdapter);

        searchKeyword= findViewById(R.id.keyword);
        imageView= findViewById(R.id.search);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 keyword= searchKeyword.getText().toString().trim();

                if(keyword==null || keyword.isEmpty()){
                    Toast.makeText(SearchActivity.this,"Text Field is Empty",Toast.LENGTH_SHORT).show();
                    newsList.clear();
                    recyclerViewAdapter.notifyDataSetChanged();
                }
                else{
                    findNews();
                }
            }
        });
    }

    private void findNews(){
        ApiUtils.getApiInterface().getSearchedNews("\""+keyword+"\"",apiKey).enqueue(new Callback<DataFetcher>() {
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