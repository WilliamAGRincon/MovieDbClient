package com.williamgiraldo.moviedbclient.entities;

public class Image {
    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getBaseImageUrl(){
        return POSTER_BASE_IMAGE_URL + this.backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String poster_path;
    private String backdrop_path;
    private int id;

    public static final String POSTER_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2";
    public static final String BACKDROP_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w780";
}
