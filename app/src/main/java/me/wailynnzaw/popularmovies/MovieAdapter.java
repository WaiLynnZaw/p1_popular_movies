package me.wailynnzaw.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<MovieResults.Movie> movies;
    ClickListener listener;
    public MovieAdapter(ArrayList<MovieResults.Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).movieTitle.setText(movies.get(position).original_title);

            Glide.with(((ViewHolder) holder).movieImage.getContext())
                    .load(Constants.IMG_PREFIX + movies.get(position).poster_path)
                    .into(((ViewHolder) holder).movieImage);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface ClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_movie)
        ImageView movieImage;

        @BindView(R.id.txt_movie)
        TextView movieTitle;

        @BindView(R.id.root_layout)
        LinearLayout touchLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            touchLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(getAdapterPosition());
            }
        }
    }
}
