package com.williamgiraldo.moviedbclient.lib;

import android.widget.ImageView;

public interface GlideImageLoaderRepository {
    void load(ImageView imageView, String URL);
}
