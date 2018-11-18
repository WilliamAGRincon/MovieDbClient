package com.williamgiraldo.moviedbclient.api;

import com.williamgiraldo.moviedbclient.models.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    @GET("/3/movie/{category}")
    Call<MoviesModel> getMovies(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );
}
