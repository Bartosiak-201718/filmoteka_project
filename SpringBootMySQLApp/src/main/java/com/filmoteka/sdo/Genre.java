package com.filmoteka.sdo;

/**
 * Created by Michal on 20.04.2018.
 */
public class Genre {

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(com.filmoteka.dao.Genre genre){
        this.name = genre.getName();
        this.id = genre.getId();
    }

    public Genre(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Long id;
    private String name;
}
