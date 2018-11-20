package com.williamgiraldo.moviedbclient.views;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.api.ApiRepositoryImp;
import com.williamgiraldo.moviedbclient.eventbus.LoadingEvent;
import com.williamgiraldo.moviedbclient.eventbus.MovieEvent;
import com.williamgiraldo.moviedbclient.images.ui.SearchableRepository;
import com.williamgiraldo.moviedbclient.images.ui.adapters.ImagesAdapter;
import com.williamgiraldo.moviedbclient.lib.GlideImageLoaderRepositoryImp;
import com.williamgiraldo.moviedbclient.entities.MoviesModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchableActivity extends AppCompatActivity implements SearchableRepository {

    @BindView(R.id.search_toolbar)
    Toolbar searchToolbar;
    @BindView(R.id.search_appbar)
    AppBarLayout searchAppbar;
    @BindView(R.id.search_progress_bar)
    ProgressBar searchProgressBar;
    @BindView(R.id.search_recycler_view)
    RecyclerView searchRecyclerView;
    @BindView(R.id.container)
    FrameLayout container;

    ApiRepositoryImp apiRepositoryImp;
    @BindView(R.id.txtView_activity_searchable)
    TextView txtViewActivitySearchable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        ButterKnife.bind(this);
        setSupportActionBar(searchToolbar);
        apiRepositoryImp = new ApiRepositoryImp();
        initViews();
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchToolbar.setTitle(getString(R.string.search_query, query));
            apiRepositoryImp = new ApiRepositoryImp();
            apiRepositoryImp.apiCallSearch(query);
        }
    }

    private void initViews() {
        int orientation = getResources().getConfiguration().orientation;
        RecyclerView.LayoutManager layoutManager;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(getApplicationContext(), 4);
        } else {
            layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        }

        searchRecyclerView.setHasFixedSize(true);
        searchRecyclerView.setLayoutManager(layoutManager);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchEvent(MovieEvent event) {
        MoviesModel moviesModel = event.message.body();
        List<MoviesModel.ResultsBean> listOfMovies = moviesModel.getResults();

        if (listOfMovies.size() == 0){
            hideProgressBar();
            txtViewActivitySearchable.setVisibility(View.VISIBLE);
            return;
        }

        for (int i = 0; i < listOfMovies.size(); i++) {
            if (listOfMovies.get(i).getPoster_path() == null) {
                listOfMovies.remove(i);
            }
        }

        GlideImageLoaderRepositoryImp glideImageLoaderRepositoryImp = new GlideImageLoaderRepositoryImp(Glide.with(this));
        ImagesAdapter adapter = new ImagesAdapter((ArrayList<MoviesModel.ResultsBean>) listOfMovies,
                getApplicationContext(), glideImageLoaderRepositoryImp);

        searchRecyclerView.setAdapter(adapter);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void showProgressBar(LoadingEvent loadingEvent) {
        if (loadingEvent.isLoading()) {
            searchProgressBar.setVisibility(View.VISIBLE);
        } else {
            hideProgressBar();
        }
    }

    @Override
    public void hideProgressBar() {
        searchProgressBar.setVisibility(View.GONE);
    }
}
