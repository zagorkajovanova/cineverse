package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.enums.Role;
import mk.ukim.finki.wp.cineverse.model.exceptions.InvalidUserException;
import mk.ukim.finki.wp.cineverse.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.cineverse.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.cineverse.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.cineverse.repository.ClientRepository;
import mk.ukim.finki.wp.cineverse.repository.UserRepository;
import mk.ukim.finki.wp.cineverse.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException(userId));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> register(String username, String password, String repeatPassword, String name, String surname, LocalDate birthDate,
                                   String address, String email, Role role) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }

        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }



        User user = new User(username, this.passwordEncoder.encode(password), name, surname, birthDate,
                address, email, role);
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public User update(Long userId, String username, String name, String surname, LocalDate birthDate,
                       String address) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException(userId));


        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setBirthDate(birthDate);
        user.setAddress(address);
        return this.userRepository.save(user);
    }

    @Override
    public Optional<Movie> addToFavoriteMovies(User user, Movie movie) {
        List<Movie> favoriteMovies = user.getFavoriteMovies();
        favoriteMovies.add(movie);

        user.setFavoriteMovies(favoriteMovies);
        this.userRepository.save(user);
        return Optional.of(movie);
    }

    @Override
    public Optional<Movie> removeFromFavoriteMovies(User user, Movie movie) {
        List<Movie> favoriteMovies = user.getFavoriteMovies();
        favoriteMovies.remove(movie);

        user.setFavoriteMovies(favoriteMovies);
        this.userRepository.save(user);
        return Optional.of(movie);
    }
}
