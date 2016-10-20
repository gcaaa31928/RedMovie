package com.red.redmovie.movie.presenter;

import com.red.redmovie.beans.MoviesBean;
import com.red.redmovie.movie.model.MoviesModel;
import com.red.redmovie.movie.model.MoviesModelImpl;
import com.red.redmovie.movie.model.OnLoadMoviesListListener;
import com.red.redmovie.movie.view.MoviesView;

import java.util.List;

/**
 * Created by Red on 2016/10/20.
 */

public class MoviesPresenterImpl implements MoviesPresenter, OnLoadMoviesListListener {
    private MoviesModel mMoviesModel;
    private MoviesView mMoviesView;

    public MoviesPresenterImpl(MoviesView moviesView){
        mMoviesModel = new MoviesModelImpl();
        mMoviesView = moviesView;
    }

    @Override
    public void loadMovies(int type, int page) {

    }

    @Override
    public void onFailure(String msg, Exception e) {

    }

    @Override
    public void onSuccess(List<MoviesBean> list) {

    }
}
