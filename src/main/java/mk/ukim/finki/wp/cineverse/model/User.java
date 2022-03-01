package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;
import mk.ukim.finki.wp.cineverse.model.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String address;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Movie> favoriteMovies;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public User() {
        this.favoriteMovies = new ArrayList<>();
    }

    public User(String username, String password, String name, String surname, LocalDate birthDate,
                String address, String email, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.role = role;
        this.favoriteMovies = new ArrayList<>();
        this.tickets = new ArrayList<>();

    }

}
