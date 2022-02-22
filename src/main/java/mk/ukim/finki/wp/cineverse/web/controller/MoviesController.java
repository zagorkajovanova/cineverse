package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Movie> horrorMovies = this.movieService.findMoviesByGenre("Horror");
        List<Movie> comedyMovies = this.movieService.findMoviesByGenre("Comedy");
        List<Movie> romanceMovies = this.movieService.findMoviesByGenre("Romance");

        List<Movie> fantasyComedy = Stream.concat(fantasyMovies.stream(), comedyMovies.stream())
                .collect(Collectors.toList());

        model.addAttribute("movies", movies);
        model.addAttribute("fantasyMovies", fantasyComedy);
        model.addAttribute("actionMovies", actionMovies);
        model.addAttribute("horrorMovies", horrorMovies);
        model.addAttribute("romanceMovies", romanceMovies);

        return "movies";
    }

    @GetMapping("/add-movie")
    public String getAddMoviePage(){
        //TODO: create add-movie page
        return "add-movie";
    }

    //TODO: implement PostMapping for add movie

}
