package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Client;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> listAllTicketsByClient(Client client);
    List<Ticket> listAll();
    Optional<Ticket> findById(Long id);
    Ticket create(Client client, Movie movie, Integer numberOfSeats);
    void deleteTicketById(Long id);
}
