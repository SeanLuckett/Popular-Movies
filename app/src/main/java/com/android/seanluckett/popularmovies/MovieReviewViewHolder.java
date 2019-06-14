package com.android.seanluckett.popularmovies;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MovieReviewViewHolder extends RecyclerView.ViewHolder {
    final TextView reviewAuthor, reviewText;

    public MovieReviewViewHolder(@NonNull View itemView) {
        super(itemView);

        reviewAuthor = itemView.findViewById(R.id.review_author);
        reviewText = itemView.findViewById(R.id.review_text);
    }

}
