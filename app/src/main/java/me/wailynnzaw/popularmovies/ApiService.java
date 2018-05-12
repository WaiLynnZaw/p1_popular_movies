package me.wailynnzaw.popularmovies;

import com.google.gson.JsonElement;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(Constants.DISCOVER_MOVIE_POPULAR)
    Call<MovieResults> getPopularMovies(@Query("page")int page, @Query("api_key") String apiKey);


    class Implementation {
        static ApiService get() {
            return getBuilder().build().create(ApiService.class);
        }

        static Retrofit.Builder getBuilder() {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor).build();

            return new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.END_POINT)
                    .client(client);
        }
    }
}
