package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String title;
    private String genre;
    private String description;

    @OneToOne
    private Image poster;
    private String trailerUrl;
    private Date showtime;
    private String duration;
    private Long ticketPrice;
    private String director;
    private String writer;

    @ManyToMany
    private List<Actor> actors;

    public Movie(Long movieId, String title, String genre, String description,
                 Image poster, String trailerUrl, Date showtime, String duration,
                 Long ticketPrice, String director, String writer, List<Actor> actors) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.poster = poster;
        this.trailerUrl = trailerUrl;
        this.showtime = showtime;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
    }
}
