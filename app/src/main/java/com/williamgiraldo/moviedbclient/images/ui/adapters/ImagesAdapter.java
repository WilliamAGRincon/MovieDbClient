package com.williamgiraldo.moviedbclient.images.ui.adapters;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.entities.Image;
import com.williamgiraldo.moviedbclient.lib.GlideImageLoaderRepositoryImp;
import com.williamgiraldo.moviedbclient.entities.MoviesModel;
import com.williamgiraldo.moviedbclient.views.MovieDetailActivity;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private ArrayList<MoviesModel.ResultsBean> dataset;
    private GlideImageLoaderRepositoryImp imageLoader;
    private Context context;

    public ImagesAdapter(ArrayList<MoviesModel.ResultsBean> androidString,
                            Context context, GlideImageLoaderRepositoryImp imageLoader) {

        this.dataset = androidString;
        this.context = context;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        if (this.dataset.get(i).getPoster_path() != null){
            String imageUrl = Image.POSTER_BASE_IMAGE_URL.concat(this.dataset.get(i).getPoster_path());
            imageLoader.load(viewHolder.img_android,imageUrl);
            viewHolder.img_android.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra("title", dataset.get(i).getTitle());
                        if (dataset.get(i).getBackdrop_path() != null){
                            intent.putExtra("image", dataset.get(i).getBackdrop_path());
                        }
                        intent.putExtra("overview", dataset.get(i).getOverview());
                        intent.putExtra("id", dataset.get(i).getId());
                        intent.putExtra("release_date", dataset.get(i).getRelease_date());

                        context.startActivity(intent);
                    }
                    catch (NullPointerException e){
                        Log.e("NullPointerException", e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_android;

        public ViewHolder(View view) {
            super(view);
            img_android = view.findViewById(R.id.ImageViewFragment);
        }
    }
}