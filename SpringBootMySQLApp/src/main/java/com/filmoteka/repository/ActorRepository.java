package com.filmoteka.repository;

import com.filmoteka.dao.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Michal on 14.04.2018.
 */
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
