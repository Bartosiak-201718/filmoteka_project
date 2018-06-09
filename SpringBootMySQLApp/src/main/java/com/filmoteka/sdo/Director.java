package com.filmoteka.sdo;

/**
 * Created by Michal on 20.04.2018.
 */
public class Director {

    public Director(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Director(com.filmoteka.dao.Director director){
        this.firstName = director.getFirstName();
        this.lastName = director.getLastName();
        this.id = director.getId();
    }

    public Director(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private Long id;
    private String firstName;
    private String lastName;
}
