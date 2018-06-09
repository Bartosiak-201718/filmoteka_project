package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
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

    public Movie getById(Long id){
        return movieRepository.getOne(id);
    }


    public void deleteById(Long id){
        try {
            movieRepository.deleteById(id);
        }
        catch(Exception e){
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.Movie createMovie (com.filmoteka.sdo.Movie movie){
        Movie m = new Movie();
        m.setTitle(movie.getTitle());
        m.setDescriptionOfMovie(movie.getDescriptionOfMovie());
        m.setDuration(movie.getDuration());
        m.setReleaseDate(movie.getReleaseDate());

        m = movieRepository.save(m);
        com.filmoteka.sdo.Movie saved = new com.filmoteka.sdo.Movie(m);
        return saved;
    }

    public com.filmoteka.sdo.Movie updateMovie (com.filmoteka.sdo.Movie movie, Long id) throws IncorrectIdException {

        Movie movie1 = new Movie();
        if(!movie.getId().equals(id)) {
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

    public List<com.filmoteka.sdo.Movie> getAllMovies(){
        List<com.filmoteka.sdo.Movie>movieList= new ArrayList<>();
        for (Movie movieDao: movieRepository.findAll()) {
            com.filmoteka.sdo.Movie movie = new com.filmoteka.sdo.Movie(movieDao);
            movieList.add(movie);
        }
        return movieList;
    }
}
