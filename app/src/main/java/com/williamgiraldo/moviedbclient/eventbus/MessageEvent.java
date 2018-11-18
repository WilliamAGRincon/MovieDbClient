package com.williamgiraldo.moviedbclient.eventbus;

import com.williamgiraldo.moviedbclient.models.MoviesModel;

import retrofit2.Response;

public class MessageEvent {
    public final Response<MoviesModel> message;
    public MessageEvent(Response<MoviesModel> response) {
        this.message = response;
    }
}