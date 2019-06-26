package com.android.seanluckett.popularmovies;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.google.android.material.tabs.TabLayout;

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

                ViewPager viewPager = findViewById(R.id.movie_detail_pager);

                MovieDetailPagerAdapter pagerAdapter = new MovieDetailPagerAdapter(
                    getSupportFragmentManager(), selectedMovie, getApplication()
                );

                viewPager.setAdapter(pagerAdapter);

                TabLayout tabs = findViewById(R.id.movie_detail_tabs);
                tabs.setupWithViewPager(viewPager);
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
