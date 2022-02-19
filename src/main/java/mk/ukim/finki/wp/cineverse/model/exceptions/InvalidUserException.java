package mk.ukim.finki.wp.cineverse.model.exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(Long id){
        super(String.format("Invalid user: %d", id));
    }
}
