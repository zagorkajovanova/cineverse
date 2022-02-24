package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Ticket;
import mk.ukim.finki.wp.cineverse.model.User;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> listAllTicketsByUser(User user);
    List<Ticket> listAll();
    Optional<Ticket> findById(Long id);
    Ticket create(User client, Movie movie, Integer numberOfSeats);
    void deleteTicketById(Long id);
}
