package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SeatController {

    private final MovieService movieService;

    public SeatController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("movie/{id}/seats")
    public String getSeatsPage(@PathVariable String id, Model model){
        Long movieId = Long.parseLong(id);
        Movie movie = this.movieService.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));

        model.addAttribute("movie", movie);

        model.addAttribute("style1", "seats.css");
        model.addAttribute("script1","seats.js");
        return "seats";
    }
}
