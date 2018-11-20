package com.williamgiraldo.moviedbclient.eventbus;

public class VideoEvent {
    String movie_id;
    public VideoEvent(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getVideoKey(){
        return movie_id;
    }
}
