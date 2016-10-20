package com.red.redmovie.movie.model;

/**
 * Created by Red on 2016/10/20.
 */

public interface MoviesModel {
    void loadMovies(String url, int type, OnLoadMoviesListListener listener);

}
