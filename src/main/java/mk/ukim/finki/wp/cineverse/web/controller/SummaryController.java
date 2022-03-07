package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import mk.ukim.finki.wp.cineverse.service.TicketService;
import mk.ukim.finki.wp.cineverse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/summary")
public class SummaryController {

    private final UserService userService;
    private final MovieService movieService;
    private final TicketService ticketService;

    public SummaryController(UserService userService, MovieService movieService, TicketService ticketService) {
        this.userService = userService;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }

    @GetMapping("/{userId}/{movieId}/{numSeats}")
    private String getSummaryPage(Model model, @PathVariable Long userId,
                                  @PathVariable String movieId, @PathVariable String numSeats){
        Long id = Long.parseLong(movieId);
        Movie movie = this.movieService.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        User user = this.userService.findById(userId);
        Integer seats = Integer.parseInt(numSeats);

        model.addAttribute("movie", movie);
        model.addAttribute("user", user);
        model.addAttribute("seats", seats);
        model.addAttribute("price", seats*movie.getTicketPrice());

        model.addAttribute("style1","header-footer.css");
        model.addAttribute("style2", "summary.css");
        model.addAttribute("pageTitle", "Summary");
        model.addAttribute("bodyContent", "summary");
        return "master-template";
    }

    @GetMapping("/{username}/{movieId}/{numSeats}/confirm")
    private String confirmTicket(@PathVariable String username,
                                 @PathVariable String movieId,
                                 @PathVariable String numSeats){
        Integer num = Integer.parseInt(numSeats);
        Long id = Long.parseLong(movieId);
        User user = this.userService.findByUsername(username);
        Movie movie = this.movieService.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        this.ticketService.create(user,movie,num,(num*movie.getTicketPrice()));

        return "redirect:/success" ;
    }

}
