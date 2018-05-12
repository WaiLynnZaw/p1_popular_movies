package me.wailynnzaw.popularmovies;

import java.util.ArrayList;

public class MovieResults {
    public ArrayList<Movie> results;

    class Movie {
        public String original_title;
        public String poster_path;
        public String release_date;
    }
}
