package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Review;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.model.exceptions.ReviewNotFoundException;
import mk.ukim.finki.wp.cineverse.repository.MovieRepository;
import mk.ukim.finki.wp.cineverse.repository.ReviewRepository;
import mk.ukim.finki.wp.cineverse.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Review> listAllReviews() {
        return this.reviewRepository.findAll();
    }

    @Override
    public List<Review> listAllReviewsByMovieId(Long movieId) {
        Movie movie = this.movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        Long id = movie.getMovieId();
        return this.reviewRepository.findAll()
                .stream().filter(r -> r.getMovie().getMovieId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Review create(String comment, Movie movie, User author) {
        Review review = new Review(comment,movie,author);
        return this.reviewRepository.save(review);
    }

    @Override
    public void removeAllReviewsByMovie(Movie movie) {
        this.reviewRepository.removeAllByMovie(movie);
    }

    @Override
    public void removeById(Long reviewId) {
        Review review = this.reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        this.reviewRepository.delete(review);
    }

    @Override
    public List<Review> getLatestReviews() {
        return this.reviewRepository.findAll().stream()
                .sorted(Comparator.comparing(Review::getReviewDate).reversed()).limit(2).collect(Collectors.toList());
    }
}
