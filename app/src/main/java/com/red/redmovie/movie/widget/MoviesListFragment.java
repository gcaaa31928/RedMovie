package com.red.redmovie.movie.widget;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.red.redmovie.R;
import com.red.redmovie.beans.MoviesBean;
import com.red.redmovie.movie.MoviesAdapter;
import com.red.redmovie.movie.presenter.MoviesPresenter;
import com.red.redmovie.movie.presenter.MoviesPresenterImpl;
import com.red.redmovie.movie.view.MoviesView;
import com.red.redmovie.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


public class MoviesListFragment extends Fragment implements MoviesView, SwipeRefreshLayout.OnRefreshListener {

    private final static String TAG = "MoviesListFragment";
    private int mType = MoviesFragment.MOVIES_TYPE_HOT;
    private int pageIndex = 1;
    private MoviesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private MoviesPresenter mMoviesPresenter;
    private List<MoviesBean> mData;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static MoviesListFragment newInstance(int type) {
        MoviesListFragment fragment = new MoviesListFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesAdapter.OnItemClickListener mOnItemClickListener = new MoviesAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) return;
            MoviesBean movie = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movie", movie);
            View transitionView = view.findViewById(R.id.poster);
            ActivityOptionsCompat  options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            transitionView, getString(R.string.transitions_movie_poster));
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMoviesPresenter = new MoviesPresenterImpl(this);
        mType = getArguments().getInt("type");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MoviesAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItemPos;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItemPos = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItemPos + 1 == mAdapter.getItemCount()) {
                LogUtils.d(TAG, "loading");
                mMoviesPresenter.loadMovies(mType, pageIndex + 1);
            }
        }
    };

    public void addMovies(List<MoviesBean> moviesList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(moviesList);
        mAdapter.setmData(mData);
        pageIndex += 1;
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        if (mData != null) mData.clear();
        mMoviesPresenter.loadMovies(mType, pageIndex);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
