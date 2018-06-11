package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.User;
import com.filmoteka.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.getOne(id);
    }


    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.User createUser(com.filmoteka.sdo.User user) {
        User u = new User();
        u.setCity(user.getCity());
        u.setLogin(user.getLogin());
        u.setPassword(user.getPassword());
        u.setUserName(user.getUserName());
        u = userRepository.save(u);
        com.filmoteka.sdo.User saved = new com.filmoteka.sdo.User(u);
        return saved;
    }

    public com.filmoteka.sdo.User updateUser(com.filmoteka.sdo.User user, Long id) throws IncorrectIdException {

        User user1 = new User();
        if (!user.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        user1 = userRepository.getOne(id);
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setLogin(user.getLogin());
        user1.setCity(user.getCity());
        user1 = userRepository.save(user1);
        com.filmoteka.sdo.User saved = new com.filmoteka.sdo.User(user1);
        return saved;
    }

    public List<com.filmoteka.sdo.User> getAllUsers() {
        List<com.filmoteka.sdo.User> userList = new ArrayList<>();
        for (User userDao : userRepository.findAll()) {
            com.filmoteka.sdo.User user = new com.filmoteka.sdo.User(userDao);
            userList.add(user);
        }
        return userList;
    }
}
