package mk.ukim.finki.wp.cineverse.repository;

import mk.ukim.finki.wp.cineverse.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
