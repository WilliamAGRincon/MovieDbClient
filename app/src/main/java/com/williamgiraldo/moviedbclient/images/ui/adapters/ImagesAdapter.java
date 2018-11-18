package com.williamgiraldo.moviedbclient.images.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.entities.Image;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    //private ArrayList<Image> android;
    private OnItemClickListener listener;
    private ArrayList<String> dataset;
    private Context context;

    /*public ImagesAdapter(Context context, ArrayList<Image> android) {
        this.android = android;
        this.context = context;
    }*/

    public ImagesAdapter(OnItemClickListener listener, ArrayList<String> androidString, Context context) {
        this.listener = listener;
        this.dataset = androidString;
        this.context = context;
    }

    /*public ImagesAdapter(Context context, ArrayList<String> android) {
        this.dataset = android;

        this.context = context;
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //String imageUrl = Image.BASE_IMAGE_URL.concat(this.dataset.get(i));
        Glide.with(context).load(this.dataset.get(i))
                .into(viewHolder.img_android);
        //ImageLoader imageLoader = null;
        //imageLoader.load(viewHolder.img_android,android.get(i).getPoster_path());
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