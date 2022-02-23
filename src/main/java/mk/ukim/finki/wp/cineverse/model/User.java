package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;
import mk.ukim.finki.wp.cineverse.model.enums.Role;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String avatarURL;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String username, String password, String name, String surname,
                String email, String avatarURL, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;

        if(avatarURL!=null && !avatarURL.isEmpty()) {
            this.avatarURL = avatarURL;
        }else{
            this.avatarURL = "/img/user.png";
        }
    }
}
