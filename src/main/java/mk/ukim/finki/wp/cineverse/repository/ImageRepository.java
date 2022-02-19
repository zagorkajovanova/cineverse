package mk.ukim.finki.wp.cineverse.repository;

import mk.ukim.finki.wp.cineverse.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
