package com.williamgiraldo.moviedbclient.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.api.ApiRepositoryImp;
import com.williamgiraldo.moviedbclient.api.MovieService;
import com.williamgiraldo.moviedbclient.entities.Image;
import com.williamgiraldo.moviedbclient.eventbus.MessageEvent;
import com.williamgiraldo.moviedbclient.images.ui.adapters.ImagesAdapter;
import com.williamgiraldo.moviedbclient.images.ui.ImagesRepository;
import com.williamgiraldo.moviedbclient.images.ui.ImagesView;
import com.williamgiraldo.moviedbclient.models.MoviesModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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


    Unbinder unbinder;
    @BindView(R.id.card_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    FrameLayout container;
    ApiRepositoryImp apiRepositoryImp = new ApiRepositoryImp("popular",1);

    List<String> itemsImage = new ArrayList<String>();
    //private ArrayList<String> itemsImage;

    public fragment_movie() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.movie_fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRetainInstance(true);
        initViews();
        getImages();
        //apiRepositoryImp.apiCall();
        return view;
    }

    private void initViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
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
    public void onItemClick(Image image) {
        Intent intent = new Intent(getContext(),MovieDetailActivity.class);
        intent.putExtra("Hola", image.getId());
        startActivity(intent);
    }

    @Override
    public void getImages() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MoviesModel> call =
                movieService.getMovies("popular",
                        "d02f2fa3f664c03a07e09aa5bb6be5cb",
                        "es",
                        1);

        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                //EventBus.getDefault().post(new MessageEvent(response));
                onMessageEventTest(response);
                //EventBus.getDefault().postSticky(new MessageEvent(response));
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e("onFailure",t.getLocalizedMessage());
            }
        });
    }

    public void onMessageEventTest(Response<MoviesModel> response) {
        MoviesModel moviesModel = response.body();
        List<MoviesModel.ResultsBean> listOfMovies = moviesModel.getResults();
        for (int i=0; i < listOfMovies.size(); i++){
            itemsImage.add(Image.BASE_IMAGE_URL.concat(listOfMovies.get(i).getBackdrop_path()));
        }
        //ImagesAdapter adapter = new ImagesAdapter(getContext(), (ArrayList<String>) itemsImage);
        ImagesAdapter adapter = new ImagesAdapter(null,(ArrayList<String>) itemsImage, getContext());
        recyclerView.setAdapter(adapter);
    }

    //@Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        MoviesModel moviesModel = event.message.body();
        List<MoviesModel.ResultsBean> listOfMovies = moviesModel.getResults();
        for (int i=0; i < listOfMovies.size(); i++){
            itemsImage.add(Image.BASE_IMAGE_URL.concat(listOfMovies.get(i).getBackdrop_path()));
        }
        //ImagesAdapter adapter = new ImagesAdapter(getContext(), (ArrayList<String>) itemsImage);
        ImagesAdapter adapter = new ImagesAdapter(null,(ArrayList<String>) itemsImage, getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
