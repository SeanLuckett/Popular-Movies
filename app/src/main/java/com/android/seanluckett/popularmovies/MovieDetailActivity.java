package com.android.seanluckett.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra(FilmData.TAG)) {
                FilmData selectedMovie = intent.getParcelableExtra(FilmData.TAG);
                getSupportActionBar().setTitle(selectedMovie.getTitle());

                // Can't wait to use data binding
                ImageView posterView = findViewById(R.id.detail_poster_image);
                Picasso.get()
                    .load(selectedMovie.getPosterImagePath())
                    .into(posterView);

                TextView rating = findViewById(R.id.detail_rating_text);
                rating.setText(selectedMovie.getUserRating().toString());

                TextView releaseDate = findViewById(R.id.detail_release_date);
                releaseDate.setText(selectedMovie.getReleaseDate());

                TextView plot = findViewById(R.id.plot_summary);
                plot.setText(selectedMovie.getPlot());

            }
        }
    }
}
