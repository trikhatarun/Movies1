package com.android.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by trikh on 31-12-2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Movie> {

    private ArrayList<com.android.movies.Movie> MovieList;
    private ImageView thumbnail;
    private TextView name;

    public MovieAdapter(ArrayList<com.android.movies.Movie> list){
        MovieList = list;
    }

    @Override
    public Movie onCreateViewHolder(ViewGroup parent, int viewType) {
        View list_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout,parent,false);
        return new Movie(list_item);
    }

    @Override
    public void onBindViewHolder(Movie holder, int position) {
        name.setText(MovieList.get(position).getmName());
    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    public class Movie extends RecyclerView.ViewHolder {
        public Movie(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            name = (TextView) itemView.findViewById(R.id.movie_name);
        }
    }
}