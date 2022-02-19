package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

import java.util.Date;

@Data
public class Review {

    private Long reviewId;
    private String comment;
    //Date when the review is posted
    private Date reviewDate;

    //Review for which movie
    private Movie movie;
    //Author of the review
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
