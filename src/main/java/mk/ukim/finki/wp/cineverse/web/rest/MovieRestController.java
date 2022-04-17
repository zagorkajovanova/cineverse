package mk.ukim.finki.wp.cineverse.web.rest;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/movies")
class MovieRestController {

    private final MovieService movieService;

    MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    private List<Movie> findAll() {
        return this.movieService.listAllMovies();
    }
}