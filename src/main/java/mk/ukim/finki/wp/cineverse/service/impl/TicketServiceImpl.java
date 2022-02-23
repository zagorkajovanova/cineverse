package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Client;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Ticket;
import mk.ukim.finki.wp.cineverse.repository.TicketRepository;
import mk.ukim.finki.wp.cineverse.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> listAllTicketsByClient(Client client) {
        return this.ticketRepository.findAllByClient(client);
    }

    @Override
    public List<Ticket> listAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return this.ticketRepository.findById(id);
    }

    @Override
    public Ticket create(Client client, Movie movie, Integer numberOfSeats) {
        Ticket ticket = new Ticket(client,movie,numberOfSeats);
        return this.ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicketById(Long id) {
        this.ticketRepository.deleteById(id);
    }
}
