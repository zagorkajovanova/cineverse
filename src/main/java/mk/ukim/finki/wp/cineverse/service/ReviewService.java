package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Client;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Review;

import java.util.Date;
import java.util.List;

public interface ReviewService {
    List<Review> listAllReviews();
    List<Review> listAllReviewsByMovieId(Long movieId);
    Review create(String comment, Movie movie, Client author);
    void removeAllReviewsByMovie(Movie movie);
}
