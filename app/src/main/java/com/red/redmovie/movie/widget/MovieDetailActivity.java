package com.red.redmovie.movie.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.red.redmovie.R;
import com.red.redmovie.beans.MoviesBean;
import com.red.redmovie.utils.ImageLoaderUtils;

public class MovieDetailActivity extends AppCompatActivity {

    private MoviesBean mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mMovie = (MoviesBean) getIntent().getSerializableExtra("movie");

        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.poster_image), mMovie.getPosterPath());

    }
}
