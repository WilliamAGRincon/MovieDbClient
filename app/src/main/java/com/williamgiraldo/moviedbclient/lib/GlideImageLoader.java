package com.williamgiraldo.moviedbclient.lib;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        glideRequestManager
                .load(URL)
                .into(imageView);
    }
}
