package com.android.seanluckett.popularmovies.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.seanluckett.popularmovies.R;


public class MovieReviewViewHolder extends RecyclerView.ViewHolder {
    public final TextView reviewAuthor;
    public final TextView reviewText;

    public MovieReviewViewHolder(@NonNull View itemView) {
        super(itemView);

        reviewAuthor = itemView.findViewById(R.id.review_author);
        reviewText = itemView.findViewById(R.id.review_text);
    }

}
