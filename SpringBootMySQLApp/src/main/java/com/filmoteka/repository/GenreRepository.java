package com.filmoteka.repository;

import com.filmoteka.dao.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Michal on 14.04.2018.
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
