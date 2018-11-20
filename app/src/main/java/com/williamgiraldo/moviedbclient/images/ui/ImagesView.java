package com.williamgiraldo.moviedbclient.images.ui;

public interface ImagesView {
    /***
     *  Show progress bar in fragment
     */
    void showProgressBar();

    /***
     * Hide progress bar in fragment
     */
    void hideProgressBar();

    /***
     * Handle error messages in fragment
     * @param error
     */
    void onError(String error);
}
