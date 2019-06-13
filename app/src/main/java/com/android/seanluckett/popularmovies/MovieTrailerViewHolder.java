package com.android.seanluckett.popularmovies;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.seanluckett.popularmovies.models.TrailerData;


public class MovieTrailerViewHolder extends RecyclerView.ViewHolder implements
    View.OnClickListener {

    final TextView trailerName, trailerType, trailerSize;
    private final MovieTrailersOnClickHandler clickHandler;
    private TrailerData selectedTrailer;

    MovieTrailerViewHolder(@NonNull View view, MovieTrailersOnClickHandler handler) {
        super(view);
        clickHandler = handler;

        trailerName = itemView.findViewById(R.id.trailer_name);
        trailerType = itemView.findViewById(R.id.trailer_type);
        trailerSize = itemView.findViewById(R.id.trailer_size);
        view.setOnClickListener(this);
    }

    public void bindData(TrailerData trailer) { selectedTrailer = trailer; }

    @Override
    public void onClick(View v) {
        clickHandler.onTrailerClicked(selectedTrailer);
    }
}
