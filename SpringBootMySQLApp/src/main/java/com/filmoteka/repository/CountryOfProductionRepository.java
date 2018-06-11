package com.filmoteka.repository;

import com.filmoteka.dao.CountryOfProduction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Michal on 14.04.2018.
 */
public interface CountryOfProductionRepository extends JpaRepository<CountryOfProduction, Long> {
}
