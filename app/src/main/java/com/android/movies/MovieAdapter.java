package com.android.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by trikh on 31-12-2016.
 */

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Movie> {

    private ArrayList<com.android.movies.Movie> MovieList;
    private ImageView thumbnail;
    private TextView name;
    private Context mContext;

    MovieAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Movie onCreateViewHolder(ViewGroup parent, int viewType) {
        View list_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout,parent,false);
        return new Movie(list_item);
    }

    @Override
    public void onBindViewHolder(Movie holder, int position) {
        name.setText(MovieList.get(position).getmName());
        Picasso.with(mContext).load(MovieList.get(position).getmImageUrl()).into(thumbnail);
    }

    @Override
    public int getItemCount() {
        if (MovieList == null)
            return 0;
        return MovieList.size();
    }

    void setMovieList(ArrayList<com.android.movies.Movie> movieList) {
        MovieList = movieList;
        notifyDataSetChanged();
    }

    class Movie extends RecyclerView.ViewHolder {
        Movie(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            name = (TextView) itemView.findViewById(R.id.movie_name);
        }
    }
}
