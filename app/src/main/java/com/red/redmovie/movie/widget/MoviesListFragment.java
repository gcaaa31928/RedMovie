package com.red.redmovie.movie.widget;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoviesListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoviesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesListFragment extends Fragment implements MoviesView {

    private int mType = MoviesFragment.MOVIES_TYPE_HOT;
    private int pageIndex = 0;
    private MoviesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private MoviesPresenter mMoviesPresenter;
    private List<MoviesBean> mData;

    public static MoviesListFragment newInstance(int type) {
        MoviesListFragment fragment = new MoviesListFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

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
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MoviesAdapter(getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void addMovies(List<MoviesBean> moviesList) {
        if(mData == null) {
            mData = new ArrayList<MoviesBean>();
        }
        mData.addAll(moviesList);
        mAdapter.setmData(mData);
    }
}
