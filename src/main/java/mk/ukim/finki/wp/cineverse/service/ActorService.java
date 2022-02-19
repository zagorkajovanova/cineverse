package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    List<Actor> findAll();
    Optional<Actor> findById(Long actorId);
}
