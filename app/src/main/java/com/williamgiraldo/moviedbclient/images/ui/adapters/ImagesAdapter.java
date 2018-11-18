package com.williamgiraldo.moviedbclient.images.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.entities.Image;
import com.williamgiraldo.moviedbclient.lib.GlideImageLoader;
import com.williamgiraldo.moviedbclient.models.MoviesModel;
import com.williamgiraldo.moviedbclient.views.MovieDetailActivity;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    //private ArrayList<Image> android;
    private OnItemClickListener listener;
    private ArrayList<MoviesModel.ResultsBean> dataset;
    private GlideImageLoader imageLoader;
    private Context context;

    public ImagesAdapter(OnItemClickListener listener, ArrayList<MoviesModel.ResultsBean> androidString, Context context, GlideImageLoader imageLoader) {
        this.listener = listener;
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
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        String imageUrl = Image.POSTER_BASE_IMAGE_URL.concat(this.dataset.get(i).getPoster_path());
        imageLoader.load(viewHolder.img_android,imageUrl);
        viewHolder.img_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("title", dataset.get(i).getTitle());
                intent.putExtra("image", dataset.get(i).getBackdrop_path());
                intent.putExtra("overview", dataset.get(i).getOverview());
                intent.putExtra("id", dataset.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_android;
        private View view;

        public ViewHolder(View view) {
            super(view);
            img_android = view.findViewById(R.id.ImageViewFragment);
        }

        public void setOnClickListener(Image image, OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}