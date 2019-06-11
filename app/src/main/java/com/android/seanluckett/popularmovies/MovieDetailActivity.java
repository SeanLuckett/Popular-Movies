package com.android.seanluckett.popularmovies;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

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
                getSupportActionBar().setTitle(this.getString(R.string.movie_details_title));

                TextView movieTitle = findViewById(R.id.movie_title_text);
                movieTitle.setText(selectedMovie.getTitle());

                ImageView posterView = findViewById(R.id.detail_poster_image);
                MovieApiWrapper.loadPosterIntoView(posterView, selectedMovie);

                TextView rating = findViewById(R.id.detail_rating_text);
                rating.setText(selectedMovie.getUserRating().toString());

                TextView releaseDate = findViewById(R.id.detail_release_date);
                releaseDate.setText(selectedMovie.getReleaseDate());

                TextView plot = findViewById(R.id.plot_summary);
                plot.setText(selectedMovie.getPlot());

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
