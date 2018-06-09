package com.filmoteka.sdo;

import java.util.List;

/**
 * Created by Michal on 20.04.2018.
 */
public class User {

    private Long id;
    private String login;
    private String password;
    private String userName;
    private String city;

    public User(Long id, String login, String password, String userName, String city) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.city = city;
    }

    public User(){

    }

    public User(com.filmoteka.dao.User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.userName = user.getUserName();
        this.city = user.getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    ////////////////////////////////

    private List<Movie> awaitingMovie;
    private List<Movie> favouriteMovie;

}
