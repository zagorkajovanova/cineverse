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
    private Client client;
    @ManyToOne
    private Movie movie;

    //Booked seats
    private Integer numberOfSeats;
    //Calculated price of the ticket
    private Long price;

    public Ticket() {

    }

    public Ticket(Client client, Movie movie, Integer numberOfSeats) {
        this.client = client;
        this.movie = movie;
        this.numberOfSeats = numberOfSeats;
    }
}
