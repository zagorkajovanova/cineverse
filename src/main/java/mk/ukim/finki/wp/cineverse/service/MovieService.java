package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.model.Image;
import mk.ukim.finki.wp.cineverse.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAllMovies();
    Optional<Movie> findById(Long id);
    Movie removeById(Long id);
    Movie removeByTitle(String title);
    Movie save(String title, String genre, String description,
               Image poster, String trailerUrl, String showtime, String duration,
               Float ticketPrice, String director, String writer, List<Actor> actors);
    Movie update(Long movieId, String title, String genre, String description,
                 Image poster, String trailerUrl, String showtime, String duration,
                 Float ticketPrice, String director, String writer, List<Actor> actors);
    List<Movie> getLatestMovies();
    Movie selectRandomMovie();
    List<Movie> findMoviesByGenre(String genre);
}