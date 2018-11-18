package com.williamgiraldo.moviedbclient.views;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.williamgiraldo.moviedbclient.R;
import com.williamgiraldo.moviedbclient.images.ui.adapters.MoviePageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupAdapter();
    }

    private void setupAdapter() {
        String[] titles = new String[]{
                getString(R.string.main_tab_title_popular),
                getString(R.string.main_tab_title_toprated),
                getString(R.string.main_tab_title_upcoming)};
        MoviePageAdapter adapter = new MoviePageAdapter(getSupportFragmentManager(),titles);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(viewpager);
        tabs.getTabAt(0).setIcon(R.drawable.ic_whatshot_black_24dp);
        tabs.getTabAt(1).setIcon(R.drawable.ic_show_chart_black_24dp);
        tabs.getTabAt(2).setIcon(R.drawable.ic_today_black_24dp);
    }


}
