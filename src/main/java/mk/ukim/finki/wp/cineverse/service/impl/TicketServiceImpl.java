package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Ticket;
import mk.ukim.finki.wp.cineverse.model.User;
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
    public List<Ticket> listAllTicketsByUser(User user) {
        return this.ticketRepository.findAllByUser(user);
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
    public Ticket create(User user, Movie movie, Integer numberOfSeats) {
        Ticket ticket = new Ticket(user,movie,numberOfSeats);
        return this.ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicketById(Long id) {
        this.ticketRepository.deleteById(id);
    }
}