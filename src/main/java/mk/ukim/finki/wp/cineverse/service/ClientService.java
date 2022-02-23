package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Client;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.User;

import java.util.Optional;

public interface ClientService {
    User findById(Long id);
    Client update(Long userId, String username, String name, String surname,
                  String email, String avatarURL, String birthDate, String address);
    Optional<Movie> addToFavoriteMovies(Client client, Movie movie);
    Optional<Movie> removeFromFavoriteMovies(Client client, Movie movie);
}
