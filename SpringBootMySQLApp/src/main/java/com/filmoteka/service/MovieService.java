package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.Award;
import com.filmoteka.dao.Distributor;
import com.filmoteka.dao.Movie;
import com.filmoteka.repository.MovieRepository;
import com.filmoteka.sdo.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    final Logger logger = LogManager.getLogger(MovieService.class);

    @Autowired
    MovieRepository movieRepository;

    public Movie getById(Long id) {
        return movieRepository.getOne(id);
    }


    public void deleteById(Long id) {
        try {
            movieRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.Movie createMovie(com.filmoteka.sdo.Movie movie) {
        Movie m = new Movie();
        m.setTitle(movie.getTitle());
        m.setDescriptionOfMovie(movie.getDescriptionOfMovie());
        m.setDuration(movie.getDuration());
        m.setReleaseDate(movie.getReleaseDate());

        Distributor distributor = new Distributor();
        distributor.setName(movie.getDistributor().getName());
        distributor.setId(movie.getDistributor().getId());
        m.setDistributor(distributor);

        com.filmoteka.dao.CountryOfProduction countryOfProduction = new com.filmoteka.dao.CountryOfProduction();
        countryOfProduction.setId(movie.getCountryOfProduction().getId());
        countryOfProduction.setName(movie.getCountryOfProduction().getName());
        m.setCountryOfProduction(countryOfProduction);

        com.filmoteka.dao.Genre genre = new com.filmoteka.dao.Genre();
        genre.setId(movie.getGenre().getId());
        genre.setName(movie.getGenre().getName());
        m.setGenre(genre);

        com.filmoteka.dao.Director director = new com.filmoteka.dao.Director();
        director.setId(movie.getDirector().getId());
        director.setFirstName(movie.getDirector().getFirstName());
        director.setLastName(movie.getDirector().getLastName());
        m.setDirector(director);

        com.filmoteka.dao.Actor actor1 = new com.filmoteka.dao.Actor();
        actor1.setId(movie.getActorList().get(0).getId());
        actor1.setFirstName(movie.getActorList().get(0).getFirstName());
        actor1.setLastName(movie.getActorList().get(0).getLastName());
        List<com.filmoteka.dao.Actor> actors = new ArrayList<>();
        actors.add(actor1);
        m.setActors(actors);
        Award award = new Award();
        award.setId(movie.getAwardList().get(0).getId());
        award.setName(movie.getAwardList().get(0).getName());
        List<Award> awards = new ArrayList<>();
        m.setAwards(awards);

        m = movieRepository.save(m);
        com.filmoteka.sdo.Movie saved = new com.filmoteka.sdo.Movie(m);
        return saved;
    }

    public com.filmoteka.sdo.Movie updateMovie(com.filmoteka.sdo.Movie movie, Long id) throws IncorrectIdException {

        Movie movie1 = new Movie();
        if (!movie.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        movie1 = movieRepository.getOne(id);
        movie1.setTitle(movie.getTitle());
        movie1.setReleaseDate(movie.getReleaseDate());
        movie1.setDuration(movie.getDuration());
        movie1.setDescriptionOfMovie(movie.getDescriptionOfMovie());
        movie1 = movieRepository.save(movie1);
        com.filmoteka.sdo.Movie saved = new com.filmoteka.sdo.Movie(movie1);
        return saved;
    }

    public List<com.filmoteka.sdo.Movie> getAllMovies() {
        List<com.filmoteka.sdo.Movie> movieList = new ArrayList<>();
        for (Movie movieDao : movieRepository.findAll()) {
            com.filmoteka.sdo.Movie movie = new com.filmoteka.sdo.Movie(movieDao);
            movieList.add(movie);
        }
        return movieList;
    }
}
