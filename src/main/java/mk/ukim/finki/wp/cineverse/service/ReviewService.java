package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Review;
import mk.ukim.finki.wp.cineverse.model.User;

import java.util.List;

public interface ReviewService {
    List<Review> listAllReviews();
    List<Review> listAllReviewsByMovieId(Long movieId);
    Review create(String comment, Movie movie, User author);
    void removeAllReviewsByMovie(Movie movie);
    void removeById(Long reviewId);
    List<Review> getLatestReviews();
}
