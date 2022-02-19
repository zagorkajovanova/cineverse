package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Movie {

    private Long movieId;
    private String name;
    private String genre;
    private String description;
    private Image poster;
    private String trailerUrl;
    private Date showtime;
    private String duration;
    private Long ticketPrice;
    private String director;
    private String writer;
    private Actor actors;

    public Movie(Long movieId, String name, String genre, String description, Image poster,
                 String trailerUrl, Date showtime, String duration, Long ticketPrice,
                 String director, String writer, Actor actors) {
        this.movieId = movieId;
        this.name = name;
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
