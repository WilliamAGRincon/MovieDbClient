package com.williamgiraldo.moviedbclient.images.ui;

import com.williamgiraldo.moviedbclient.eventbus.LoadingEvent;

public interface SearchableRepository {
    void showProgressBar(LoadingEvent loadingEvent);
    void hideProgressBar();
}
