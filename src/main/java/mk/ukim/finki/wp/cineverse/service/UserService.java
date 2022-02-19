package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.User;

import java.util.Optional;

public interface UserService {
    User findById(Long userId);
    Optional<User> findByUsername(String username);
    Optional<User> register(String username, String password, String repeatPassword, String name, String surname,
                            String email, String avatarURL, String role, String birthDate, String address);
    User update(Long userId, String username, String name, String surname,
                String email, String avatarURL);
}
