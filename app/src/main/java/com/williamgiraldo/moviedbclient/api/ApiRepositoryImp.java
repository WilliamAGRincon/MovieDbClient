package com.williamgiraldo.moviedbclient.api;

import android.util.Log;

import com.williamgiraldo.moviedbclient.entities.Video;
import com.williamgiraldo.moviedbclient.eventbus.LoadingEvent;
import com.williamgiraldo.moviedbclient.eventbus.MovieEvent;
import com.williamgiraldo.moviedbclient.eventbus.VideoEvent;
import com.williamgiraldo.moviedbclient.entities.MoviesModel;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepositoryImp implements ApiRepositoryView{
    public static final String API_KEY = "d02f2fa3f664c03a07e09aa5bb6be5cb";
    public static final String BASE_URL = "https://api.themoviedb.org";


    @Override
    public void apiCallSearch(String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MoviesModel> call =
                movieService.searchMovies(
                        API_KEY,
                        "es",
                         query,
                         1);

        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                EventBus.getDefault().post(new MovieEvent(response));
                EventBus.getDefault().post(new LoadingEvent(false));
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                onError(t.getMessage());
            }
        });
    }

    @Override
    public void apiCallGetMovieVideoId(int movie_id, String lang) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Call<Video> call =
                movieService.getMoviesVideos(movie_id,
                        API_KEY,
                        lang);

        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                if (response.body().getResults().size() > 0)
                    EventBus.getDefault().post(new VideoEvent(response.body().getResults().get(0).getKey()));
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                onError(t.getMessage());
            }
        });
    }

    @Override
    public void onError(String error) {
        Log.e("onError", error);
    }
}
