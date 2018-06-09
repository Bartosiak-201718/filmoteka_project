package com.filmoteka.sdo;

import com.filmoteka.dao.*;
import com.filmoteka.dao.CountryOfProduction;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Michal on 20.04.2018.
 */
public class Movie {

    public Movie(Long id, String title, LocalDateTime releaseDate, String descriptionOfMovie, Integer duration) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.descriptionOfMovie = descriptionOfMovie;
        this.duration = duration;
    }

    public Movie(){

    }

    public Movie(com.filmoteka.dao.Movie movie){
        this.title = movie.getTitle();
        this.releaseDate = movie.getReleaseDate();
        this.descriptionOfMovie = movie.getDescriptionOfMovie();
        this.duration = movie.getDuration();
        this.id = movie.getId();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescriptionOfMovie() {
        return descriptionOfMovie;
    }

    public void setDescriptionOfMovie(String descriptionOfMovie) {
        this.descriptionOfMovie = descriptionOfMovie;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    ///////////////////////////////////////

    private Long id;
    private String title;
    private LocalDateTime releaseDate;
    private String descriptionOfMovie;
    private Integer duration;
    private List<Actor> actorList;
    private Distributor distributor;
    private List<Award> awardList;
    private Director director;
    private CountryOfProduction countryOfProduction;
    private Genre genre;


    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public CountryOfProduction getCountryOfProduction() {
        return countryOfProduction;
    }

    public void setCountryOfProduction(CountryOfProduction countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    public Movie(Long id, String title, LocalDateTime releaseDate, String descriptionOfMovie, Integer duration, List<Actor> actorList, Distributor distributor, List<Award> awardList, Director director, CountryOfProduction countryOfProduction, Genre genre) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.descriptionOfMovie = descriptionOfMovie;
        this.duration = duration;
        this.actorList = actorList;
        this.distributor = distributor;
        this.awardList = awardList;
        this.director = director;
        this.countryOfProduction = countryOfProduction;
        this.genre = genre;
    }

}
