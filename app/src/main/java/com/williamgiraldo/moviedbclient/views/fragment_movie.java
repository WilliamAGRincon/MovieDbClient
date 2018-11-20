package com.williamgiraldo.moviedbclient.views;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.api.ApiRepositoryImp;
import com.williamgiraldo.moviedbclient.api.MovieService;
import com.williamgiraldo.moviedbclient.entities.MoviesModel;
import com.williamgiraldo.moviedbclient.images.ui.ImagesRepository;
import com.williamgiraldo.moviedbclient.images.ui.ImagesView;
import com.williamgiraldo.moviedbclient.images.ui.adapters.ImagesAdapter;
import com.williamgiraldo.moviedbclient.lib.GlideImageLoaderRepositoryImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_movie extends Fragment implements ImagesView, ImagesRepository {
    public static final String ARG_OBJECT = "object";

    Unbinder unbinder;
    @BindView(R.id.card_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    FrameLayout container;
    Bundle bundle;
    @BindView(R.id.txtView_fragment_content_error)
    TextView txtViewFragmentContentError;

    public fragment_movie() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.movie_fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRetainInstance(true);
        initViews();
        getMovieImages();
        return view;
    }

    private void initViews() {
        int orientation = getResources().getConfiguration().orientation;
        RecyclerView.LayoutManager layoutManager;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(getContext(), 4);
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        bundle = getArguments();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void getMovieImages() {
        String movie_category;
        if (bundle.getInt(ARG_OBJECT) == 0) {
            movie_category = "popular";
        } else if (bundle.getInt(ARG_OBJECT) == 1) {
            movie_category = "top_rated";
        } else {
            movie_category = "upcoming";
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiRepositoryImp.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        showProgressBar();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<MoviesModel> call =
                movieService.getMovies(movie_category,
                        ApiRepositoryImp.API_KEY,
                        "es",
                        1);

        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                onMovieEvent(response);
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                txtViewFragmentContentError.setVisibility(View.VISIBLE);
                hideProgressBar();
                onError(t.getMessage());
            }
        });
    }

    public void onMovieEvent(Response<MoviesModel> response) {
        MoviesModel moviesModel = response.body();
        List<MoviesModel.ResultsBean> listOfMovies = moviesModel.getResults();
        GlideImageLoaderRepositoryImp glideImageLoaderRepositoryImp = new GlideImageLoaderRepositoryImp(Glide.with(this));
        ImagesAdapter adapter = new ImagesAdapter((ArrayList<MoviesModel.ResultsBean>) listOfMovies, getContext(), glideImageLoaderRepositoryImp);

        recyclerView.setAdapter(adapter);
    }
}
