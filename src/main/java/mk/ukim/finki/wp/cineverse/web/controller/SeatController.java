package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Ticket;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import mk.ukim.finki.wp.cineverse.service.TicketService;
import mk.ukim.finki.wp.cineverse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class SeatController {

    private final MovieService movieService;
    private final UserService userService;
    private final TicketService ticketService;

    public SeatController(MovieService movieService, UserService userService, TicketService ticketService) {
        this.movieService = movieService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping("movie/{id}/seats")
    public String getSeatsPage(@PathVariable String id, Model model){
        Long movieId = Long.parseLong(id);
        Movie movie = this.movieService.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));

        model.addAttribute("movie", movie);

        model.addAttribute("style1", "seats-page.css");
        model.addAttribute("style2", "header-and-footer.css");
        model.addAttribute("script1","chooseSeats.js");
        model.addAttribute("pageTitle", "Seats");
        model.addAttribute("bodyContent", "seats");
        return "master-template";
    }

    @PostMapping("{movieId}/seats/{username}")
    public String getSeats(@RequestParam String numSeats, @PathVariable String username,
                           @PathVariable String movieId, HttpServletRequest request){
        Long id = Long.parseLong(movieId);
        Movie movie = this.movieService.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        Integer num = Integer.parseInt(numSeats);
        User user = this.userService.findByUsername(username);

        //TODO: create ticket and implement summary controller
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("numSeats", num);
        request.getSession().setAttribute("movie", movie);
        request.getSession().setAttribute("price", num*movie.getTicketPrice());
        return "redirect:/summary/"+ username + "/" +movieId+ "/" +numSeats;
    }
}
