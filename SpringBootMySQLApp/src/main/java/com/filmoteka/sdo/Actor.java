package com.filmoteka.sdo;

public class Actor {

    public Actor(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Actor(com.filmoteka.dao.Actor actor) {
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.id = actor.getId();
    }

    public Actor() {

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
