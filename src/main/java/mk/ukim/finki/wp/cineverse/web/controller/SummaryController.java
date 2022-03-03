package mk.ukim.finki.wp.cineverse.web.controller;

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

@Controller
@RequestMapping("/summary")
public class SummaryController {

    private final UserService userService;
    private final MovieService movieService;

    public SummaryController(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping("/{username}/{movieId}/{numSeats}")
    private String getSummaryPage(Model model, @PathVariable String username,
                                  @PathVariable String movieId, @PathVariable String numSeats){
        Long id = Long.parseLong(movieId);
        Movie movie = this.movieService.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        User user = this.userService.findByUsername(username);
        Integer seats = Integer.parseInt(numSeats);

        model.addAttribute("movie", movie);
        model.addAttribute("user", user);
        model.addAttribute("seats", seats);
        model.addAttribute("price", seats*movie.getTicketPrice());

        return "summary";
    }

    //TODO: PostMapping
    //this.ticketService.create(user,movie,num,(num*movie.getTicketPrice()));
}
