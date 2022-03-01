package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public String getMoviePage(Model model, @PathVariable String id){
        Long movieId = Long.parseLong(id);
        Movie movie = this.movieService.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        String [] pom = movie.getPoster().getImageUrl().split("\\.");
        String [] pom1 = pom[0].split("_");
        String [] durationPom = movie.getDuration().split(" ");
        List<Actor> actors = movie.getActors();

        String movieYear = pom1[pom1.length-1];

        model.addAttribute("movie", movie);
        model.addAttribute("movieYear", movieYear);
        model.addAttribute("movieHours", durationPom[0]);
        model.addAttribute("movieMins", durationPom[1]);
        model.addAttribute("pageTitle", movie.getTitle());
        model.addAttribute("actors", actors);

        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("style2", "single-movie-display.css");
        model.addAttribute("style3", "movies-page-title.css");
        model.addAttribute("bodyContent", "movie");
        return "master-template";
    }

    @GetMapping("{id}/trailer")
    public String getTrailer(Model model, @PathVariable String id) {
        Long movieId = Long.parseLong(id);
        Movie movie = this.movieService.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));

        model.addAttribute("movie", movie);
        model.addAttribute("pageTitle", "Movie Trailer");
        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("bodyContent", "trailer");
        return "master-template";
    }

    //TODO: implement addToFavorites
}
