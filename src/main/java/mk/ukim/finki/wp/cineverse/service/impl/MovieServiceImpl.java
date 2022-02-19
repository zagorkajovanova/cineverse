package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.model.Image;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.repository.MovieRepository;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAllMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Movie removeById(Long id) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        this.movieRepository.delete(movie);
        return movie;
    }

    @Override
    public Movie save(String title, String genre, String description, Image poster, String trailerUrl,
                      String showtime, String duration, Long ticketPrice, String director, String writer, List<Actor> actors) {
        LocalDate date = LocalDate.parse(showtime);
        Movie movie = new Movie(title,genre,description,poster,trailerUrl,date,duration,ticketPrice,director,writer,actors);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie update(Long movieId, String title, String genre, String description, Image poster, String trailerUrl,
                        String showtime, String duration, Long ticketPrice, String director, String writer, List<Actor> actors) {
        Movie movie = this.movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));

        if (trailerUrl == null || trailerUrl.isEmpty()) {
            trailerUrl = movie.getTrailerUrl();
        }
        if(poster == null){
            poster = movie.getPoster();
        }
        LocalDate date = LocalDate.parse(showtime);

        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDescription(description);
        movie.setPoster(poster);
        movie.setTrailerUrl(trailerUrl);
        movie.setShowtime(date);
        movie.setDuration(duration);
        movie.setTicketPrice(ticketPrice);
        movie.setDirector(director);
        movie.setWriter(writer);
        movie.setActors(actors);

        return this.movieRepository.save(movie);
    }
}
