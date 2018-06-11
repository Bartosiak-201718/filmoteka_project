package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.Genre;
import com.filmoteka.repository.GenreRepository;
import com.filmoteka.sdo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    final Logger logger = LogManager.getLogger(GenreService.class);

    @Autowired
    GenreRepository genreRepository;

    public Genre getById(Long id) {
        return genreRepository.getOne(id);
    }


    public void deleteById(Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.Genre createGenre(com.filmoteka.sdo.Genre genre) {
        Genre g = new Genre();
        g.setName(genre.getName());
        g = genreRepository.save(g);
        com.filmoteka.sdo.Genre saved = new com.filmoteka.sdo.Genre(g);
        return saved;
    }

    public com.filmoteka.sdo.Genre updateGenre(com.filmoteka.sdo.Genre genre, Long id) throws IncorrectIdException {

        Genre genre1 = new Genre();
        if (!genre.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        genre1 = genreRepository.getOne(id);
        genre1.setName(genre.getName());
        genre1 = genreRepository.save(genre1);
        com.filmoteka.sdo.Genre saved = new com.filmoteka.sdo.Genre(genre1);
        return saved;
    }

    public List<com.filmoteka.sdo.Genre> getAllGenres() {
        List<com.filmoteka.sdo.Genre> genreList = new ArrayList<>();
        for (Genre genreDao : genreRepository.findAll()) {
            com.filmoteka.sdo.Genre genre = new com.filmoteka.sdo.Genre(genreDao);
            genreList.add(genre);
        }
        return genreList;
    }
}
