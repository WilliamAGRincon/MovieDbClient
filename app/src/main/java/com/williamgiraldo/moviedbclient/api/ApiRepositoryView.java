package com.williamgiraldo.moviedbclient.api;

public interface ApiRepositoryView {
    /**
     * Api call to moviedb search Api
     * @param query: query
     */
    void apiCallSearch(String query);

    /***
     * Api call to moviedb video Api
     * @param movie_id: Video id
     * @param lang: Language
     */
    void apiCallGetMovieVideoId(int movie_id, String lang);

    /***
     * Method to handle error
     * @param error: error message
     */
    void onError(String error);
}
