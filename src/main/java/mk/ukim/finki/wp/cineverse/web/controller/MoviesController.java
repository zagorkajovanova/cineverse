package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.model.Image;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.service.ActorService;
import mk.ukim.finki.wp.cineverse.service.ImageService;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import mk.ukim.finki.wp.cineverse.service.impl.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    public MoviesController(MovieService movieService, ImageService imageService, FileService fileService, ActorService actorService) {
        this.movieService = movieService;
        this.imageService = imageService;
        this.fileService = fileService;
        this.actorService = actorService;
    }

    @GetMapping
    public String getMoviesPage(Model model){
        Movie randomMovie = this.movieService.selectRandomMovie();
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

        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("style2", "movies-page-title.css");
        model.addAttribute("style3", "movies-carousel.css");
        model.addAttribute("bodyContent", "movies");
        return "master-template";
    }

    @GetMapping("/add-movie")
    public String getAddMoviePage(Model model){
        model.addAttribute("style1", "header-and-footer.css");
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

//        String[] pom = actors.split(",");
//        List<Actor> actorList = new ArrayList<>();
//        for (String s : pom) {
//            String[] fullName = s.split(" ");
//            String actorName = fullName[0];
//            String actorSurname = fullName[1];
//            actorList.add(new Actor(actorName, actorSurname));
//            this.actorService.save(actorName,actorSurname);
//        }

        //TODO: add actors from input
        List<Actor> actors1 = this.actorService.findAll().subList(0,3);
        Float ticketPrice = Float.parseFloat(price);
        Image image = this.imageService.save(FilepathConstants.IMAGE_DESTINATION_PREFIX + posterUrl.getOriginalFilename());
        fileService.uploadFile(posterUrl);

        this.movieService.save(title,genre,description, image, trailerUrl,date,duration,ticketPrice,director,writer,actors1);
        return "redirect:/movies";
    }

}
