package com.williamgiraldo.moviedbclient.api;

import android.util.Log;

import com.williamgiraldo.moviedbclient.eventbus.MessageEvent;
import com.williamgiraldo.moviedbclient.models.MoviesModel;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepositoryImp implements ApiRepositoryView{
    private String category;
    private int page;
    public ApiRepositoryImp(String category, int page) {
        this.category = category;
        this.page = page;
    }

    @Override
    public void apiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MoviesModel> call =
                movieService.getMovies(category,
                        "d02f2fa3f664c03a07e09aa5bb6be5cb",
                        "es",
                        page);

        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                EventBus.getDefault().post(new MessageEvent(response));
                //EventBus.getDefault().postSticky(new MessageEvent(response));
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e("onFailure",t.getLocalizedMessage());
            }
        });
    }
}
