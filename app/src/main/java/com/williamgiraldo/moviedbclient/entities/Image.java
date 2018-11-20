package com.williamgiraldo.moviedbclient.entities;

public class Image {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;

    public static final String POSTER_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2";
    public static final String BACKDROP_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w780";
}
