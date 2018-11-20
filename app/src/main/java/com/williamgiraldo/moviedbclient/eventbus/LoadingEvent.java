package com.williamgiraldo.moviedbclient.eventbus;

public class LoadingEvent {
    boolean isLoading;
    public LoadingEvent(Boolean isLoading) {
        this.isLoading = isLoading;
    }

    public boolean isLoading() {
        return isLoading;
    }
}
