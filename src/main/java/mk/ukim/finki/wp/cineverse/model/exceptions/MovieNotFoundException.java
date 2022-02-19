package mk.ukim.finki.wp.cineverse.model.exceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(Long movieId){
        super(String.format("Movie with id %d not found!", movieId));
    }
}
