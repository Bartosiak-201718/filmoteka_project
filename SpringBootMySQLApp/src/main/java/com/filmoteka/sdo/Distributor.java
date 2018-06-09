package com.filmoteka.sdo;

/**
 * Created by Michal on 20.04.2018.
 */
public class Distributor {

    public Distributor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Distributor(com.filmoteka.dao.Distributor distributor){
        this.name = distributor.getName();
        this.id = distributor.getId();
    }

    public Distributor(){

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
