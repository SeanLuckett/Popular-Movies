package com.android.seanluckett.popularmovies.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.seanluckett.popularmovies.clickHandlers.MovieTrailersOnClickHandler;
import com.android.seanluckett.popularmovies.R;
import com.android.seanluckett.popularmovies.models.TrailerData;


public class MovieTrailerViewHolder extends RecyclerView.ViewHolder implements
    View.OnClickListener {

    public final TextView trailerName;
    public final TextView trailerType;
    public final TextView trailerSize;
    private final MovieTrailersOnClickHandler clickHandler;
    private TrailerData selectedTrailer;

    public MovieTrailerViewHolder(@NonNull View view, MovieTrailersOnClickHandler handler) {
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
