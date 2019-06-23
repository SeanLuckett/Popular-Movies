package com.android.seanluckett.popularmovies;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.seanluckett.popularmovies.recyclerViewAdapters.MovieReviewsAdapter;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.models.ReviewData;
import com.android.seanluckett.popularmovies.viewModels.MovieReviewsViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieReviewsFragment extends Fragment {
    private RecyclerView movieReviewRecyclerView;
    private TextView noReviewsMessage;
    private MovieReviewsAdapter reviewsAdapter;
    private FilmData movie;

    public MovieReviewsFragment() {
        // Required empty public constructor
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewsAdapter = new MovieReviewsAdapter();
        movie = getArguments().getParcelable(MovieDetailPagerAdapter.MOVIE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_reviews, container, false);
        noReviewsMessage = view.findViewById(R.id.missing_reviews_text);

        movieReviewRecyclerView = view.findViewById(R.id.rv_reviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
            container.getContext(), RecyclerView.VERTICAL, false
        );

        movieReviewRecyclerView.setLayoutManager(layoutManager);
        movieReviewRecyclerView.setAdapter(reviewsAdapter);
        movieReviewRecyclerView.setHasFixedSize(true);

        loadReviews();


        return view;
    }

    public static MovieReviewsFragment newInstance(FilmData movie) {

        Bundle args = new Bundle();
        args.putParcelable(MovieDetailPagerAdapter.MOVIE_KEY, movie);

        MovieReviewsFragment fragment = new MovieReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void loadReviews() {
        MovieReviewsViewModel reviewModel =
            ViewModelProviders.of(getActivity()).get(MovieReviewsViewModel.class);

        reviewModel
            .getReviews(movie.getId())
            .observe(getActivity(), new Observer<ArrayList<ReviewData>>() {
                @Override
                public void onChanged(ArrayList<ReviewData> reviews) {
                    if (reviews != null && !reviews.isEmpty()) {
                        reviewsAdapter.setReviewListData(reviews);
                    } else {
                        showNoReviews();
                    }
                }
            });

    }

    private void showNoReviews() {
        movieReviewRecyclerView.setVisibility(View.INVISIBLE);
        noReviewsMessage.setVisibility(View.VISIBLE);
    }
}
