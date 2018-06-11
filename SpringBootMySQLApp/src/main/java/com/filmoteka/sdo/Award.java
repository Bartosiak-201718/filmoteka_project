package com.filmoteka.sdo;

/**
 * Created by Michal on 20.04.2018.
 */
public class Award {

    public Award(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Award(com.filmoteka.dao.Award award) {
        this.name = award.getName();
        this.id = award.getId();
    }

    public Award() {

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
