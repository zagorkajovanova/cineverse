package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/remove")
public class RemoveMovieController {

    private final MovieService movieService;

    public RemoveMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String getRemoveMoviePage(Model model){
        List<Movie> movies = this.movieService.listAllMovies();

        model.addAttribute("movies", movies);
        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("style2", "add-movie.css");
        model.addAttribute("pageTitle", "Remove Movie");
        model.addAttribute("bodyContent", "remove-movie");
        return "master-template";
    }

    @PostMapping("/movie")
    public String removeMovie(@RequestParam Long id){
        this.movieService.removeById(id);
        return "redirect:/movies";
    }
}
