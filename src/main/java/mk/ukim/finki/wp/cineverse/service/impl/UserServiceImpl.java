package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Client;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.enums.Role;
import mk.ukim.finki.wp.cineverse.model.exceptions.InvalidUserException;
import mk.ukim.finki.wp.cineverse.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.cineverse.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.cineverse.repository.ClientRepository;
import mk.ukim.finki.wp.cineverse.repository.UserRepository;
import mk.ukim.finki.wp.cineverse.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
    }

    @Override
    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException(userId));
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> register(Long userId, String username, String password, String repeatPassword,
                                   String name, String surname, String email, String avatarURL, String role, String birthDate, String address) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }

        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        if(avatarURL==null || avatarURL.isEmpty()){
            avatarURL = "";
        }

        if(!role.isEmpty() && role.equals("ROLE_CLIENT")){
            LocalDate date = LocalDate.parse(birthDate);
            Client client = new Client(username,this.passwordEncoder.encode(password),name,surname,email,avatarURL,date,address);
            return Optional.of(this.clientRepository.save(client));
        }else{
            Role role1 = Role.ROLE_ADMIN;
            LocalDate date = LocalDate.parse(birthDate);
            User user = new User(username,this.passwordEncoder.encode(password),name,surname,email,avatarURL,role1);
            return Optional.of(this.userRepository.save(user));
        }
    }

    @Override
    public User update(Long userId, String username, String name, String surname, String email, String avatarURL) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException(userId));

        if(avatarURL==null || avatarURL.isEmpty()){
            avatarURL = "";
        }

        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setAvatarURL(avatarURL);
        return this.userRepository.save(user);
    }
}
