package mk.ukim.finki.wp.cineverse.repository;

import mk.ukim.finki.wp.cineverse.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
}
