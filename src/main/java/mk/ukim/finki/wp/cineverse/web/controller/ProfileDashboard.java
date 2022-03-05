package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Ticket;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.exceptions.ClientNotFoundException;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import mk.ukim.finki.wp.cineverse.service.TicketService;
import mk.ukim.finki.wp.cineverse.service.UserService;
import mk.ukim.finki.wp.cineverse.service.impl.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileDashboard {

    private final UserService userService;
    private final TicketService ticketService;
    private final FileService fileService;
    private final MovieService movieService;

    public ProfileDashboard(UserService userService, TicketService ticketService, FileService fileService, MovieService movieService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.fileService = fileService;
        this.movieService = movieService;
    }

    @GetMapping("/user/{username}")
    public String getProfileDashboard(@PathVariable String username, Model model){
        User user = null;
        try {
            user = (User) this.userService.findByUsername(username);
        } catch (ClientNotFoundException exception) {
            return "redirect:/home?error=" + "profileDashboard";
        }
        List<Ticket> tickets = this.ticketService.listAllTicketsByUser(user);

        String role;
        if(user.getRole().toString().equals("ROLE_USER")){
            role = "User";
        }else{
            role = "Admin";
        }

        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("style2", "profile.css");
        model.addAttribute("bodyContent", "user-profile");
        model.addAttribute("pageTitle", "Profile");
        model.addAttribute("tickets", tickets);
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String username,
                             @RequestParam String address,
                             @RequestParam String email,
                             @RequestParam String birthDate,
                             @RequestParam(required = false) MultipartFile thumbnail){

        if (thumbnail.getOriginalFilename() != null && !thumbnail.getOriginalFilename().isEmpty()) {
            this.fileService.uploadFile(thumbnail);
            this.userService.update(id, username,  name, surname, email, birthDate, address,
                    FilepathConstants.IMAGE_DESTINATION_PREFIX + thumbnail.getOriginalFilename());
        }else{
            this.userService.update(id, username, name, surname, email, birthDate, address, "");
        }

        return "redirect:/profile/user/" + username;
    }

    @GetMapping("/{userId}/delete/{id}")
    public String deleteTicket(@PathVariable Long id, @PathVariable Long userId){
        this.ticketService.deleteTicketById(id);
        User user = this.userService.findById(userId);
        return "redirect:/profile/user/" + user.getUsername();
    }

    @GetMapping("/remove-favorite/{userId}/{movieId}")
    public String removeFavorite(@PathVariable Long userId,
                                 @PathVariable Long movieId){
        Movie movie = this.movieService.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        User user = this.userService.findById(userId);
        this.userService.removeFromFavoriteMovies(user,movie);

        return "redirect:/profile/user/" + user.getUsername();
    }
}
