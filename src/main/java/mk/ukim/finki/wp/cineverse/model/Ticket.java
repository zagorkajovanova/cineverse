package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    private User user;
    @ManyToOne
    private Movie movie;

    //Booked seats
    private Integer numberOfSeats;
    //Calculated price of the ticket
    private Long price;

    public Ticket() {

    }

    public Ticket(User user, Movie movie, Integer numberOfSeats) {
        this.user = user;
        this.movie = movie;
        this.numberOfSeats = numberOfSeats;
    }
}
