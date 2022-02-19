package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

@Data
public class Ticket {

    private Long ticketId;
    private Client client;
    private Movie movie;
    //Booked seats
    private Integer numberOfSeats;
    //Calculated price of the ticket
    private Long price;

    public Ticket() {

    }

    public Ticket(Long ticketId, Client client, Movie movie, Integer numberOfSeats) {
        this.ticketId = ticketId;
        this.client = client;
        this.movie = movie;
        this.numberOfSeats = numberOfSeats;
    }
}
