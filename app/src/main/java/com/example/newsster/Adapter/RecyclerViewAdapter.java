package com.example.newsster.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsster.Fragment.SavedFragment;
import com.example.newsster.MainActivity;
import com.example.newsster.News;
import com.example.newsster.R;
import com.example.newsster.database.NewsDbHandler;
import com.example.newsster.webView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<News> newsList;
    private NewsDbHandler dbHandler;
    private boolean useDiff= false;

    public RecyclerViewAdapter(Context context, List<News> newsList,boolean use){
        this.context= context;
        this.newsList= newsList;
        this.useDiff= use;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dbHandler= new NewsDbHandler(parent.getContext());
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        News news= newsList.get(position);

       holder.title.setText(news.getTitle());
       holder.description.setText((news.getDescription()));
        holder.date.setText(news.getPublishedAt());
        holder.author.setText(news.getAuthor());
        Glide.with(context).load(news.getUrlToImage()).into(holder.newsImage);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void refreshRecycleView(){
        newsList= dbHandler.getAllSavedNews();
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView newsImage;
        TextView description;
        TextView date;
        TextView author;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title= itemView.findViewById(R.id.titleText);
            newsImage= itemView.findViewById(R.id.newsImage);
            description= itemView.findViewById(R.id.descriptionText);
            date= itemView.findViewById(R.id.dateText);
            author = itemView.findViewById(R.id.authorText);
            cardView= itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this::onLongClick);
        }

        @Override
        public void onClick(View view) {
            Intent intent= new Intent(context, webView.class);
            int position= this.getAdapterPosition();
            intent.putExtra("url",newsList.get(position).getUrl());
            context.startActivity(intent);
        }

        public boolean onLongClick(View view){

                int position = this.getAdapterPosition();
                News news = new News(newsList.get(position));

            AlertDialog.Builder saveDialog= new AlertDialog.Builder(context);
            if(useDiff==false) {
                saveDialog.setTitle("SAVE");
                saveDialog.setIcon(R.drawable.dialog_box_save);
                saveDialog.setMessage("Do you want to save?");
            }
            else{
                Log.v("newster","it is used");
                saveDialog.setTitle("DELETE");
                saveDialog.setIcon(R.drawable.ic_baseline_delete_24);
                saveDialog.setMessage("Do you really want to delete?");
            }

            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    Log.v("newster data","yes selected");
                    if(useDiff==false) {
                        dbHandler.addNews(news);
                        Toast.makeText(view.getContext(), "Saved Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        dbHandler.deleteContactByTitle(news);
                        Toast.makeText(view.getContext(), "Deleted Successfully",Toast.LENGTH_SHORT).show();
                        refreshRecycleView();
                    }
        }
            });

            saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            saveDialog.show();
            return true;
        }

    }
}
