package com.example.newsster.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.newsster.Fragment.SavedFragment;
import com.example.newsster.News;

import java.util.ArrayList;
import java.util.List;

public class NewsDbHandler extends SQLiteOpenHelper {
    public NewsDbHandler(@Nullable Context context) {
        super(context, Params.DB_NAME,null ,Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create= "CREATE TABLE " + Params.TABLE_NAME +  "( " + Params.KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT," +Params.KEY_TITLE + " TEXT NOT NULL UNIQUE, " + Params.KEY_URL + " TEXT , "+Params.KEY_DESCRIPTION+" TEXT, "+Params.KEY_PUBLISHED_AT+" TEXT, "+Params.KEY_URL_TO_IMAGE+" TEXT, "+Params.KEY_AUTHOR+" TEXT )";
        Log.v("newster data","database created ");
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNews(News news){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(Params.KEY_TITLE,news.getTitle());
        values.put(Params.KEY_URL,news.getUrl());
        values.put(Params.KEY_DESCRIPTION,news.getDescription());
        values.put(Params.KEY_PUBLISHED_AT,news.getPublishedAt());
        values.put(Params.KEY_URL_TO_IMAGE,news.getUrlToImage());
        values.put(Params.KEY_AUTHOR,news.getAuthor());

        db.insert(Params.TABLE_NAME,null,values);
        db.close();
    }

    public ArrayList<News> getAllSavedNews(){
        ArrayList<News> newsList= new ArrayList<>();

        SQLiteDatabase db= this.getReadableDatabase();
        String select= "SELECT * FROM "+Params.TABLE_NAME;
        Cursor cursor= db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                News news= new News();
                news.setId(Integer.parseInt(cursor.getString(0)));
                news.setTitle(cursor.getString(1));
                news.setUrl(cursor.getString(2));
                news.setDescription(cursor.getString(3));
                news.setPublishedAt(cursor.getString(4));
                news.setUrlToImage(cursor.getString(5));
                news.setAuthor(cursor.getString(6));
                newsList.add(news);
                cursor.moveToNext();
            }
        }
        if(newsList.isEmpty()){
            News news= new News();
            newsList.add(news);
        }
        db.close();
        return newsList;
    }

    public void deleteAll(){
        SQLiteDatabase db= this.getWritableDatabase();
        String delete= "DELETE FROM "+Params.TABLE_NAME;
        db.execSQL(delete);
        db.close();
    }

    public void deleteContactByTitle(News news){
        SQLiteDatabase db= this.getWritableDatabase();
        Log.v("newster",news.getId()+" delete vala");
        db.delete(Params.TABLE_NAME,Params.KEY_TITLE+"=?",new String[]{String.valueOf(news.getTitle())});
        db.close();
    }
}
