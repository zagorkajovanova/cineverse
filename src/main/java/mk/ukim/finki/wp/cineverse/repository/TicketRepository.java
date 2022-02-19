package mk.ukim.finki.wp.cineverse.repository;

import mk.ukim.finki.wp.cineverse.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
