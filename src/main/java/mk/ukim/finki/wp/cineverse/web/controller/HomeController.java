package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.Review;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import mk.ukim.finki.wp.cineverse.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    public HomeController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Model model){
        List<Movie> topMovies = this.movieService.getLatestMovies();
        List<Review> reviews = this.reviewService.getLatestReviews();
        List<Movie> movies = this.movieService.listAllMovies();
        model.addAttribute("topMovies", topMovies);
        model.addAttribute("reviews", reviews);
        model.addAttribute("movies", movies);

        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "movie-card.css");
        model.addAttribute("style3", "upcomingMovies.css");
        model.addAttribute("style4", "reviews.css");
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/about-us")
    public String getAboutUsPage(Model model){
        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("pageTitle", "About Us");
        model.addAttribute("bodyContent", "about-us");
        return "master-template";
    }

    @GetMapping("/not-found")
    public String getNotFoundPage(Model model){
        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "not-found.css");
        model.addAttribute("pageTitle", "Not Found");
        model.addAttribute("bodyContent", "not-found");
        return "master-template";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(Model model){
        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "not-found.css");
        model.addAttribute("pageTitle", "Access Denied");
        model.addAttribute("bodyContent", "access-denied");
        return "master-template";
    }

    @GetMapping("/remove-review/{reviewId}")
    public String removeReview(@PathVariable Long reviewId){
        this.reviewService.removeById(reviewId);
        return "redirect:/home";
    }
}
