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
    private String avatarURL;

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
                String address, String email, String avatarURL, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.avatarURL = avatarURL;
        this.role = role;
        this.favoriteMovies = new ArrayList<>();
        this.tickets = new ArrayList<>();

        if(avatarURL!=null && !avatarURL.isEmpty()) {
            this.avatarURL = avatarURL;
        }else{
            this.avatarURL = "/img/user.png";
        }
    }

}
