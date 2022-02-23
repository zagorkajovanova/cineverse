package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Model model){
        //TODO: master-template
        List<Movie> topMovies = this.movieService.getLatestMovies();
        model.addAttribute("topMovies", topMovies);

        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "movie-card.css");
        model.addAttribute("style3", "upcomingMovies.css");
        return "home";
    }
}
