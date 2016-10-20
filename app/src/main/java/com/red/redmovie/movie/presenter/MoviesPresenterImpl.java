package com.red.redmovie.movie.presenter;

import com.red.redmovie.beans.MoviesBean;
import com.red.redmovie.movie.model.MoviesModel;
import com.red.redmovie.movie.model.MoviesModelImpl;
import com.red.redmovie.movie.model.OnLoadMoviesListListener;
import com.red.redmovie.movie.view.MoviesView;
import com.red.redmovie.movie.widget.MoviesFragment;
import com.red.redmovie.profile.Urls;

import java.net.URL;
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
        String url = getUrl(type, page);
        mMoviesModel.loadMovies(url, type, this);
    }

    private String getUrl(int type, int page) {
        StringBuffer sb = new StringBuffer();
        if (type== MoviesFragment.MOVIES_TYPE_HOT) {
            sb.append(Urls.HOT_URL);
        }
        return sb.toString();
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }

    @Override
    public void onSuccess(List<MoviesBean> list) {

    }
}
