package com.red.redmovie.movie.view;

import com.red.redmovie.beans.MoviesBean;

import java.util.List;

/**
 * Created by Red on 2016/10/20.
 */

public interface MoviesView {
    void addMovies(List<MoviesBean> moviesList);
    void hideProgress();
    void showProgress();
}
