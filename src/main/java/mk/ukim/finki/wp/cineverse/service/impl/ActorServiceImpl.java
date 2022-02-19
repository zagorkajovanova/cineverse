package mk.ukim.finki.wp.cineverse.service.impl;

import mk.ukim.finki.wp.cineverse.model.Actor;
import mk.ukim.finki.wp.cineverse.repository.ActorRepository;
import mk.ukim.finki.wp.cineverse.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> findAll() {
        return this.actorRepository.findAll();
    }

    @Override
    public Optional<Actor> findById(Long actorId) {
        return this.actorRepository.findById(actorId);
    }
}
