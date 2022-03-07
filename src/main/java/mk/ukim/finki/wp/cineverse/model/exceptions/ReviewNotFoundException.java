package mk.ukim.finki.wp.cineverse.model.exceptions;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(Long id){
        super(String.format("Review with id %d not found!", id));
    }

}
