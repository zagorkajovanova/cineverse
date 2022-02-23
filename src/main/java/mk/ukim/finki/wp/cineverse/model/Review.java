package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String comment;
    //Date when the review is posted
    private Date reviewDate;

    //Review for which movie
    @ManyToOne
    private Movie movie;

    //Author of the review
    @ManyToOne
    private Client author;

    public Review() {
    }

    public Review(String comment, Movie movie, Client author) {
        this.comment = comment;
        this.movie = movie;
        this.author = author;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        reviewDate = new Date(System.currentTimeMillis());
    }
}
