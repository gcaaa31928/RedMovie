package com.red.redmovie.movie;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.red.redmovie.R;
import com.red.redmovie.beans.MoviesBean;
import com.red.redmovie.utils.ImageLoaderUtils;
import com.red.redmovie.utils.LogUtils;

import java.util.List;

/**
 * Created by Red on 2016/10/4.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemViewHolder> {

    List<MoviesBean> mData;
    private static final String TAG = "MoviesAdapter";
    private Context mContext;

    public MoviesAdapter(Context context) {
        this.mContext = context;
    }

    public void setmData(List<MoviesBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            MoviesBean movies = mData.get(position);
            if (movies == null) return;
            ((ItemViewHolder) holder).mTitle.setText(movies.getTitle());
            ((ItemViewHolder) holder).mDesc.setText(movies.getOverview());
            ((ItemViewHolder) holder).mScore.setText(movies.getVoteAverage());
            LogUtils.d(TAG, movies.getPosterPath());
            ImageLoaderUtils.display(mContext, holder.mMovieImg, movies.getPosterPath());

        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie, parent, false);
        ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView mTitle;
        TextView mDesc;
        TextView mScore;
        ImageView mMovieImg;

        ItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDesc = (TextView) itemView.findViewById(R.id.overview);
            mScore = (TextView) itemView.findViewById(R.id.scores);
            mMovieImg = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}
