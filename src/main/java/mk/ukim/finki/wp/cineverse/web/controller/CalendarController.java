package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final MovieService movieService;

    public CalendarController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getCalendarPage(Model model){

        List<Movie> moviesList = this.movieService.listAllMovies();
        List<String> movies = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        for(int i=0; i<moviesList.size(); i++){
            movies.add(moviesList.get(i).getTitle());
            dates.add(moviesList.get(i).getShowtime().toString());
        }
        model.addAttribute("dates", dates);
        model.addAttribute("movies", movies);
        return "calendar";

    }
}
