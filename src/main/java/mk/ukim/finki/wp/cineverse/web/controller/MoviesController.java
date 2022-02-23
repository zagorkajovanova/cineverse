package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.model.Movie;
import mk.ukim.finki.wp.cineverse.service.ActorService;
import mk.ukim.finki.wp.cineverse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
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

        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "movies-page-title.css");
        model.addAttribute("style3", "movies-carousel.css");
        return "movies";
    }

    @GetMapping("/add-movie")
    public String getAddMoviePage(Model model){
        //TODO: create add-movie page

        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "add-movie.css");
        return "add-movie";
    }

    //TODO: implement PostMapping for add movie
//    @PostMapping("/add")
//    public String saveMovie(@RequestParam String title,
//                            @RequestParam String genre,
//                            @RequestParam String director,
//                            @RequestParam String writer,
//                            @RequestParam String date,
//                            @RequestParam String duration,
//                            @RequestParam String actors,
//                            @RequestParam String price,
//                            @RequestParam String trailerUrl,
//                            @RequestParam String posterUrl,
//                            @RequestParam String description){
//
////        String[] pom = actors.split(",");
////        List<Actor> actorList = new ArrayList<>();
////        for (String s : pom) {
////            String[] fullName = s.split(" ");
////            String actorName = fullName[0];
////            String actorSurname = fullName[1];
////            actorList.add(new Actor(actorName, actorSurname));
////        }
//
//        Float ticketPrice = Float.parseFloat(price);
//
//        this.movieService.save(title,genre,description,trailerUrl,date,duration,ticketPrice,director,writer);
//        return "redirect:/movies";
//    }

}
