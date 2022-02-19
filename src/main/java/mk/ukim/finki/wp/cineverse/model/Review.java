package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

import javax.persistence.*;
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

    public Review(Long reviewId, String comment, Date reviewDate, Movie movie, Client author) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.movie = movie;
        this.author = author;
    }
}
