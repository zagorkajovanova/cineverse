package mk.ukim.finki.wp.cineverse.model.exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(Long clientId){
        super(String.format("Client with id %d was not found.", clientId));
    }
}
