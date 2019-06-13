package com.android.seanluckett.popularmovies;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.seanluckett.popularmovies.models.TrailerData;

public class MovieTrailerViewHolder extends RecyclerView.ViewHolder {
    final TextView trailerName, trailerType, trailerSize;

    private TrailerData selectedTrailer;

    public MovieTrailerViewHolder(@NonNull View itemView) {
        super(itemView);

        trailerName = itemView.findViewById(R.id.trailer_name);
        trailerType = itemView.findViewById(R.id.trailer_type);
        trailerSize = itemView.findViewById(R.id.trailer_size);
    }

    public void bindData(TrailerData trailer) { selectedTrailer = trailer; }
}
