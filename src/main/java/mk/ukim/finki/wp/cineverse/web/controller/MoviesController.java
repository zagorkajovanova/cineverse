package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.model.Image;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.cineverse.service.*;
import mk.ukim.finki.wp.cineverse.service.impl.FileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;
    private final ImageService imageService;
    private final FileService fileService;
    private final ActorService actorService;
    private final ReviewService reviewService;
    private final UserService userService;

    public MoviesController(MovieService movieService, ImageService imageService, FileService fileService, ActorService actorService, ReviewService reviewService, UserService userService) {
        this.movieService = movieService;
        this.imageService = imageService;
        this.fileService = fileService;
        this.actorService = actorService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping
    public String getMoviesPage(Model model){
        List<Movie> movies = this.movieService.listAllMovies();
        List<Movie> fantasyMovies = this.movieService.findMoviesByGenre("Fantasy");
        List<Movie> actionMovies = this.movieService.findMoviesByGenre("Action");
        List<Movie> horrorMovies = this.movieService.findMoviesByGenre("Horror");
        List<Movie> comedyMovies = this.movieService.findMoviesByGenre("Comedy");
        List<Movie> romanceMovies = this.movieService.findMoviesByGenre("Romance");

        List<Movie> fantasyComedy = Stream.concat(fantasyMovies.stream(), comedyMovies.stream())
                .collect(Collectors.toList());


        model.addAttribute("movies", movies);
        model.addAttribute("fantasyMovies", fantasyComedy);
        model.addAttribute("actionMovies", actionMovies);
        model.addAttribute("horrorMovies", horrorMovies);
        model.addAttribute("romanceMovies", romanceMovies);
        model.addAttribute("pageTitle", "Movies");

        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "movies-page-title.css");
        model.addAttribute("style3", "movies-carousel.css");
        model.addAttribute("bodyContent", "movies");
        return "master-template";
    }

    @GetMapping("/add-movie")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddMoviePage(Model model){
        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "add-movie.css");
        model.addAttribute("pageTitle", "Add movie");

        model.addAttribute("bodyContent", "add-movie");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveMovie(@RequestParam String title,
                            @RequestParam String genre,
                            @RequestParam String director,
                            @RequestParam String writer,
                            @RequestParam String date,
                            @RequestParam String duration,
                            @RequestParam String price,
                            @RequestParam String trailerUrl,
                            @RequestParam MultipartFile posterUrl,
                            @RequestParam String description){

        List<Actor> actors1 = this.actorService.findAll().subList(0,3);
        Float ticketPrice = Float.parseFloat(price);
        Image image = this.imageService.save(FilepathConstants.IMAGE_DESTINATION_PREFIX + posterUrl.getOriginalFilename());
        fileService.uploadFile(posterUrl);

        this.movieService.save(title,genre,description, image, trailerUrl,date,duration,ticketPrice,director,writer,actors1);
        return "redirect:/movies";
    }

    @GetMapping("/add-review")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getAddReviewPage(Model model){
        List<Movie> movies = this.movieService.listAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "add-movie.css");
        model.addAttribute("pageTitle", "Add review");

        model.addAttribute("bodyContent", "add-review");
        return "master-template";
    }

    @PostMapping("/add-review/{username}")
    public String saveReview(@RequestParam Long movieId,
                            @RequestParam String comment,
                            @PathVariable String username){
        Movie movie = this.movieService.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        User user = this.userService.findByUsername(username);

        this.reviewService.create(comment, movie, user);
        return "redirect:/home";
    }
}
