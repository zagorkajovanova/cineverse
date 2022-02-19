package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;
import mk.ukim.finki.wp.cineverse.model.enums.Role;

@Data
public class User {

    private Long userId;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String avatarURL;
    private Role role;

    public User() {
    }

    public User(Long userId, String username, String password, String name, String surname, String email, String avatarURL, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;

        if(avatarURL!=null && !avatarURL.isEmpty()) {
            this.avatarURL = avatarURL;
        }else{
            this.avatarURL = "/img/img-1.png";
        }
    }
}
