package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;
import mk.ukim.finki.wp.cineverse.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

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
    private String avatarUrl;


    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Movie> favoriteMovies;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

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

        this.avatarUrl = "/img/user-avatar.png";

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
