package com.williamgiraldo.moviedbclient.lib;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

public class GlideImageLoaderRepositoryImp implements GlideImageLoaderRepository {
    private RequestManager glideRequestManager;

    public GlideImageLoaderRepositoryImp(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        glideRequestManager
                .load(URL)
                .into(imageView);
    }
}
