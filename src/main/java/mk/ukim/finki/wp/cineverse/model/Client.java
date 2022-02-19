package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;
import mk.ukim.finki.wp.cineverse.model.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Client extends User{

    private LocalDate birthDate;
    private String address;
    @ManyToMany
    private List<Movie> favoriteMovies;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public Client(){
        super();
        this.favoriteMovies = new ArrayList<>();
    }

    public Client(Long userId, String username, String password, String name, String surname, String email, String avatarURL, Role role, LocalDate birthDate, String address, List<Movie> favoriteMovies) {
        super(userId, username, password, name, surname, email, avatarURL, role);
        this.birthDate = birthDate;
        this.address = address;
        this.favoriteMovies = favoriteMovies;
    }

}
