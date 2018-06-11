package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.Director;
import com.filmoteka.repository.DirectorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectorService {

    final Logger logger = LogManager.getLogger(DirectorService.class);

    @Autowired
    DirectorRepository directorRepository;

    public Director getById(Long id) {
        return directorRepository.getOne(id);
    }


    public void deleteById(Long id) {
        try {
            directorRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.Director createDirector(com.filmoteka.sdo.Director director) {
        Director director1 = new Director();
        director1.setFirstName(director.getFirstName());
        director1.setLastName(director.getLastName());
        director1 = directorRepository.save(director1);
        com.filmoteka.sdo.Director saved = new com.filmoteka.sdo.Director(director1);
        return saved;
    }


    public com.filmoteka.sdo.Director updateDirector(com.filmoteka.sdo.Director director, Long id) throws IncorrectIdException {

        Director director1 = new Director();
        if (!director.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        director1 = directorRepository.getOne(id);
        director1.setFirstName(director.getFirstName());
        director1.setLastName(director.getLastName());
        director1 = directorRepository.save(director1);
        com.filmoteka.sdo.Director saved = new com.filmoteka.sdo.Director(director1);
        return saved;
    }

    public List<com.filmoteka.sdo.Director> getAllDirectors() {
        List<com.filmoteka.sdo.Director> directorList = new ArrayList<com.filmoteka.sdo.Director>();
        for (Director directorDao : directorRepository.findAll()) {
            com.filmoteka.sdo.Director director = new com.filmoteka.sdo.Director(directorDao);
            directorList.add(director);
        }
        return directorList;
    }

}
