package com.android.seanluckett.popularmovies.recyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.seanluckett.popularmovies.viewHolders.MovieReviewViewHolder;
import com.android.seanluckett.popularmovies.R;
import com.android.seanluckett.popularmovies.models.ReviewData;

import java.util.ArrayList;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewViewHolder> {
    private ArrayList<ReviewData> reviewListData;

    @NonNull
    @Override
    public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_reviews_item, parent, false);

        return new MovieReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewViewHolder holder, int position) {
        ReviewData review = reviewListData.get(position);

        holder.reviewAuthor.setText(review.getAuthor());
        holder.reviewText.setText(review.getText());
    }

    @Override
    public int getItemCount() {
        return (reviewListData != null) ? reviewListData.size() : 0;
    }

    public void setReviewListData(ArrayList<ReviewData> reviews) {
        reviewListData = reviews;
        notifyDataSetChanged();
    }
}
