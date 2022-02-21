package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getMoviesPage(Model model){
        Movie randomMovie = this.movieService.selectRandomMovie();
        List<Movie> movies = this.movieService.listAllMovies();
        List<Movie> fantasyMovies = this.movieService.findMoviesByGenre("Fantasy");
        List<Movie> actionMovies = this.movieService.findMoviesByGenre("Action");
        List<Movie> firstHalf = movies.subList(0,10);
        List<Movie> secondHalf = movies.subList(10,movies.size());

        String[] duration = randomMovie.getDuration().split(" ");

        model.addAttribute("randomMovie", randomMovie);
        model.addAttribute("randomMovieHours", duration[0]);
        model.addAttribute("randomMovieMins", duration[1]);
        model.addAttribute("movies", movies);
        model.addAttribute("firstHalf", firstHalf);
        model.addAttribute("secondHalf", secondHalf);
        return "movies";
    }
}
