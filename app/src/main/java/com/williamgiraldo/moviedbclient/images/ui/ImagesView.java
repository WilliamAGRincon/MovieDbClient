package com.williamgiraldo.moviedbclient.images.ui;

import com.williamgiraldo.moviedbclient.entities.Image;

public interface ImagesView {
    void showProgressBar();
    void hideProgressBar();
    void onError(String error);
    void onItemClick(Image image);
}
