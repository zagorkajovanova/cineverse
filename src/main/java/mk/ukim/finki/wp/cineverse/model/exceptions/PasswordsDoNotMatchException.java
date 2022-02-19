package mk.ukim.finki.wp.cineverse.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(){
        super("Passwords do not match!!!");
    }
}
