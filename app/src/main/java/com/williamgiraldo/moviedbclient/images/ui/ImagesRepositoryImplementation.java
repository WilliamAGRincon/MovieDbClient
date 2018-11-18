package com.williamgiraldo.moviedbclient.images.ui;

import android.util.Log;

import com.williamgiraldo.moviedbclient.api.MovieService;
import com.williamgiraldo.moviedbclient.entities.Image;
import com.williamgiraldo.moviedbclient.models.MoviesModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesRepositoryImplementation implements ImagesRepository {
    Image image =  new Image();

    @Override
    public void getImages() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MoviesModel> call =
                movieService.getMovies("popular",
                "d02f2fa3f664c03a07e09aa5bb6be5cb",
                "es",
                1);

        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                List<MoviesModel> items = new ArrayList<MoviesModel>();
                MoviesModel moviesModel = response.body();
                List<MoviesModel.ResultsBean> listOfMovies = moviesModel.getResults();
                MoviesModel.ResultsBean movie = listOfMovies.get(0);
                image.setBackdrop_path(movie.getBackdrop_path());
                returnImage(image);
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e("onFailure",t.getLocalizedMessage());
            }
        });
    }

    public String returnImage(Image image) {
        return image.getBackdrop_path();
    }
}
