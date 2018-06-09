package com.filmoteka.sdo;

/**
 * Created by Michal on 20.04.2018.
 */
public class CountryOfProduction {

    public CountryOfProduction(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CountryOfProduction(com.filmoteka.dao.CountryOfProduction country){
        this.name = country.getName();
        this.id = country.getId();
    }

    public CountryOfProduction(){

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
