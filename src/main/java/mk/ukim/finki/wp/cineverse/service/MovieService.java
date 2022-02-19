package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.model.Image;
import mk.ukim.finki.wp.cineverse.model.Movie;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAllMovies();
    Optional<Movie> findById(Long id);
    Movie removeById(Long id);
    Movie save(String title, String genre, String description,
               Image poster, String trailerUrl, String showtime, String duration,
               Long ticketPrice, String director, String writer, List<Actor> actors);
    Movie update(Long movieId, String title, String genre, String description,
                 Image poster, String trailerUrl, String showtime, String duration,
                 Long ticketPrice, String director, String writer, List<Actor> actors);
}
