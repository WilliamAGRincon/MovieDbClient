package com.williamgiraldo.moviedbclient.api;

import com.williamgiraldo.moviedbclient.entities.Video;
import com.williamgiraldo.moviedbclient.entities.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    /***
     * Movies Call
     * @param category: Movie category: popular, top-rated, upcoming
     * @param api_key: developer key
     * @param language: query language(es)
     * @param page: page numer
     * @return
     */
    @GET("/3/movie/{category}")
    Call<MoviesModel> getMovies(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    /***
     * Search Call
     * @param api_key: developer key
     * @param language: query language(es)
     * @param page: page numer
     * @return
     */
    @GET("/3/search/movie")
    Call<MoviesModel> searchMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page
    );

    /***
     * Movies Call
     * @param movie_id: movie id
     * @param api_key: developer key
     * @param language: query language(es)
     * @return
     */
    @GET("/3/movie/{movie_id}/videos")
    Call<Video> getMoviesVideos(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

}
