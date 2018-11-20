package com.williamgiraldo.moviedbclient.views;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.api.ApiRepositoryImp;
import com.williamgiraldo.moviedbclient.entities.Image;
import com.williamgiraldo.moviedbclient.eventbus.VideoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.video_webview)
    WebView videoWebview;
    @BindView(R.id.trailer_text)
    TextView trailerText;
    private  String title;
    private String overview;
    private String image;
    private String release_date;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        try {
            getMovieInformationFromIntentExtras(intent);

            ApiRepositoryImp apiRepositoryImp = new ApiRepositoryImp();
            apiRepositoryImp.apiCallGetMovieVideoId(id, "es");

            setMovieInformationToLayoutFields();
        } catch (NullPointerException e) {
            Log.e("NullPointerException", e.getMessage());
        }

        configWebView();
    }

    private void setMovieInformationToLayoutFields() {
        String imageUrl = Image.BACKDROP_BASE_IMAGE_URL.concat(image);
        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions()
                        .centerCrop())
                .into(backdrop);
        toolbarLayout.setTitle(title);
        toolbarLayout.setCollapsedTitleTypeface(Typeface.SANS_SERIF);
        description.setText(overview);
        releaseDate.setText(getString(R.string.movie_detail_relase_date, release_date));
    }

    private void getMovieInformationFromIntentExtras(Intent intent) {
        title = intent.getStringExtra("title");
        overview = intent.getStringExtra("overview");
        image = intent.getStringExtra("image");
        release_date = intent.getStringExtra("release_date");
        id = intent.getIntExtra("id", 99);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void configWebView() {
        videoWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = videoWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void loadVideoInWebView(String videoKey) {
        trailerText.setVisibility(View.VISIBLE);
        String frameVideo =
                ("<iframe src='https://www.youtube.com/embed/"+videoKey+"' width='100%' height='300px' allow='autoplay; fullscreen' frameborder='0' allowfullscreen allowscriptaccess='always' scrolling='no'></iframe>");
        videoWebview.loadData(frameVideo, "text/html", "utf-8");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getVideoId(VideoEvent videoEvent) {
        loadVideoInWebView(videoEvent.getVideoKey());
    }
}
