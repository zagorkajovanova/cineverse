package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.enums.Role;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService {
    User findById(Long userId);
    Optional<User> findByUsername(String username);
    Optional<User> register(String username, String password, String repeatPassword, String name, String surname, LocalDate birthDate,
                            String address, String email, Role role);
    User update(Long userId, String username, String name, String surname, LocalDate birthDate,
                String address);
    Optional<Movie> addToFavoriteMovies(User user, Movie movie);
    Optional<Movie> removeFromFavoriteMovies(User user, Movie movie);
}
