package com.williamgiraldo.moviedbclient.images.ui.adapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.williamgiraldo.moviedbclient.views.fragment_movie;

public class MoviePageAdapter extends FragmentStatePagerAdapter {
    private String[] titles;

    public MoviePageAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int fragmentPosition) {
        Fragment fragment = new fragment_movie();
        Bundle bundle = new Bundle();
        bundle.putInt(fragment_movie.ARG_OBJECT, fragmentPosition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
