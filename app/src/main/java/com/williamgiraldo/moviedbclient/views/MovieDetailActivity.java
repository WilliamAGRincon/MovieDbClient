package com.williamgiraldo.moviedbclient.views;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.entities.Image;
import com.williamgiraldo.moviedbclient.models.MoviesModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.backdrop)
    ImageView backdrop;
    private ArrayList<MoviesModel.ResultsBean> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String overview = intent.getStringExtra("overview");
        String image = intent.getStringExtra("image");
        int id = intent.getIntExtra("id", 99);

        String imageUrl = Image.BACKDROP_BASE_IMAGE_URL.concat(image);
        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions()
                .centerCrop())
                .into(backdrop);
        toolbarLayout.setTitle(title);
        toolbarLayout.setCollapsedTitleTypeface(Typeface.SANS_SERIF);
        description.setText(overview);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
