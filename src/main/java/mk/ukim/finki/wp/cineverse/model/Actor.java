package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    private String name;
    private String surname;

    public Actor() {
    }

    public Actor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
