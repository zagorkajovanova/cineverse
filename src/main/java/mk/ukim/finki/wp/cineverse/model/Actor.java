package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

@Data
public class Actor {

    private Long actorId;
    private String name;
    private String surname;

    public Actor(Long actorId, String name, String surname) {
        this.actorId = actorId;
        this.name = name;
        this.surname = surname;
    }
}
