package mk.ukim.finki.wp.cineverse.repository;

import mk.ukim.finki.wp.cineverse.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
