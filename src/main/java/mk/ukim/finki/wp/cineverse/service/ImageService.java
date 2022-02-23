package mk.ukim.finki.wp.cineverse.service;

import mk.ukim.finki.wp.cineverse.model.Image;

import java.util.Optional;

public interface ImageService {
    Optional<Image> findById(Long id);
    void deleteById(Long id);
    Image save(String imageUrl);
    Image findByUrl(String url);
}
