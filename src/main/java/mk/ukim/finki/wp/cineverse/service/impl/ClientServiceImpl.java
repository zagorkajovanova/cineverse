package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Client;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.enums.Role;
import mk.ukim.finki.wp.cineverse.model.exceptions.ClientNotFoundException;
import mk.ukim.finki.wp.cineverse.repository.ClientRepository;
import mk.ukim.finki.wp.cineverse.service.ClientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public User findById(Long id) {
        return this.clientRepository.findById(id)
                .filter(u -> u.getRole().equals(Role.ROLE_CLIENT))
                .orElseThrow(() -> new ClientNotFoundException(id));
    }


    public Client update(Long userId, String username, String name, String surname, String email, String avatarURL, String birthDate, String address) {
        Client client = (Client) this.clientRepository.findById(userId).orElseThrow(() -> new ClientNotFoundException(userId));

        //if the avatar isn't changed
        if (avatarURL == null || avatarURL.isEmpty()) {
            avatarURL = client.getAvatarURL();
        }

        client.setUsername(username);
        client.setName(name);
        client.setSurname(surname);
        client.setEmail(email);
        client.setAvatarURL(avatarURL);
        client.setAddress(address);

        LocalDate date = LocalDate.parse(birthDate);
        client.setBirthDate(date);

        return this.clientRepository.save(client);
    }

    @Override
    public Optional<Movie> addToFavoriteMovies(Client client, Movie movie) {
        List<Movie> favoriteMovies = client.getFavoriteMovies();
        favoriteMovies.add(movie);

        client.setFavoriteMovies(favoriteMovies);
        this.clientRepository.save(client);
        return Optional.of(movie);
    }

    @Override
    public Optional<Movie> removeFromFavoriteMovies(Client client, Movie movie) {
        List<Movie> favoriteMovies = client.getFavoriteMovies();
        favoriteMovies.remove(movie);

        client.setFavoriteMovies(favoriteMovies);
        this.clientRepository.save(client);
        return Optional.of(movie);
    }
}
