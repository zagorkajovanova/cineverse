package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User findById(Long userId);
    User findByUsername(String username);
    Optional<User> register(String username, String password, String repeatPassword, String name, String surname, String birthDate,
                            String address, String email, Role role, String avatarUrl);
    User update(Long userId, String username, String name, String surname, String email, String birthDate,
                String address, String avatarUrl);
    Optional<Movie> addToFavoriteMovies(User user, Movie movie);
    Optional<Movie> removeFromFavoriteMovies(User user, Movie movie);
    User login(String username, String password);

}
