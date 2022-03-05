package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import mk.ukim.finki.wp.cineverse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movie")
public class MovieController {


    private final MovieService movieService;
    private final UserService userService;

    public MovieController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getMoviePage(Model model, @PathVariable String id, HttpServletRequest request){
        Long movieId = Long.parseLong(id);
        Movie movie = this.movieService.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        String [] pom = movie.getPoster().getImageUrl().split("\\.");
        String [] pom1 = pom[0].split("_");
        String [] durationPom = movie.getDuration().split(" ");
        List<Actor> actors = movie.getActors();

        String movieYear = pom1[pom1.length-1];

        Optional<User> optionalUser = Optional.ofNullable(this.userService.findByUsername(request.getRemoteUser()));
        if (optionalUser.isPresent())
            model.addAttribute("user", optionalUser.get().getUserId());
        else
            model.addAttribute("user", "");

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

    @GetMapping("/add-favorite/{userId}/{movieId}")
    public String addToFavorites(@PathVariable String movieId,
                                 @PathVariable Long userId){
        Long id = Long.parseLong(movieId);
        Movie movie = this.movieService.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        User user = this.userService.findById(userId);

        this.userService.addToFavoriteMovies(user,movie);
        return "redirect:/profile/user/" + user.getUsername();
    }

}
