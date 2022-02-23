package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Image;
import mk.ukim.finki.wp.cineverse.repository.ImageRepository;
import mk.ukim.finki.wp.cineverse.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Optional<Image> findById(Long id) {
        return this.imageRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.imageRepository.deleteById(id);
    }

    @Override
    public Image save(String imageUrl) {
        Image image = new Image(imageUrl);
        return this.imageRepository.save(image);
    }

    @Override
    public Image findByUrl(String url) {
        return this.imageRepository.findByImageUrl(url);
    }
}
