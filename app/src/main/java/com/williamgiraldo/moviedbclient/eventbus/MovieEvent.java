package com.williamgiraldo.moviedbclient.eventbus;

import com.williamgiraldo.moviedbclient.entities.MoviesModel;

import retrofit2.Response;

public class MovieEvent {
    public final Response<MoviesModel> message;
    public MovieEvent(Response<MoviesModel> response) {
        this.message = response;
    }
}