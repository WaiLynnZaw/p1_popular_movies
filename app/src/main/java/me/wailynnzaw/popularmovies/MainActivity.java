package me.wailynnzaw.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    MovieAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<MovieResults.Movie> movies;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        layoutManager = new GridLayoutManager(MainActivity.this, 3);
        recyclerView.setLayoutManager(layoutManager);

        movies = new ArrayList<>();
        adapter = new MovieAdapter(movies);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MovieAdapter.ClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, movies.get(position).release_date, Toast.LENGTH_SHORT).show();
            }
        });

        progressBar.setVisibility(View.VISIBLE);

        ApiService.Implementation.get().getPopularMovies(1,Constants.API_KEY).enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.body() != null) {
                    movies.addAll(response.body().results);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
