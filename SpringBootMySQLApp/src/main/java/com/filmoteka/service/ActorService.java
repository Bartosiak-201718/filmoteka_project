package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.Actor;
import com.filmoteka.repository.ActorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    final Logger logger = LogManager.getLogger(ActorService.class);

    @Autowired
    ActorRepository actorRepository;

    public Actor getById(Long id) {
        return actorRepository.getOne(id);
    }


    public void deleteById(Long id) {
        try {
            actorRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.Actor createActor(com.filmoteka.sdo.Actor actor) {
        Actor a = new Actor();
        a.setFirstName(actor.getFirstName());
        a.setLastName(actor.getLastName());
        a = actorRepository.save(a);
        com.filmoteka.sdo.Actor saved = new com.filmoteka.sdo.Actor(a);
        return saved;
    }


    public com.filmoteka.sdo.Actor updateActor(com.filmoteka.sdo.Actor actor, Long id) throws IncorrectIdException {

        Actor actor1 = new Actor();
        if (!actor.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        actor1 = actorRepository.getOne(id);
        actor1.setFirstName(actor.getFirstName());
        actor1.setLastName(actor.getLastName());
        actor1 = actorRepository.save(actor1);
        com.filmoteka.sdo.Actor saved = new com.filmoteka.sdo.Actor(actor1);
        return saved;
    }

    public List<com.filmoteka.sdo.Actor> getAllActors() {
        List<com.filmoteka.sdo.Actor> actorList = new ArrayList<com.filmoteka.sdo.Actor>();
        for (Actor actorDao : actorRepository.findAll()) {
            com.filmoteka.sdo.Actor actor = new com.filmoteka.sdo.Actor(actorDao);
            actorList.add(actor);
        }
        return actorList;
    }

}
