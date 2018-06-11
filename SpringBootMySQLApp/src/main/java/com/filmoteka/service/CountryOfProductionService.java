package com.filmoteka.service;


import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.CountryOfProduction;
import com.filmoteka.repository.CountryOfProductionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryOfProductionService {

    final Logger logger = LogManager.getLogger(CountryOfProductionService.class);

    @Autowired
    CountryOfProductionRepository countryOfProductionRepository;

    public CountryOfProduction getById(Long id) {
        return countryOfProductionRepository.getOne(id);
    }


    public void deleteById(Long id) {
        try {
            countryOfProductionRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.CountryOfProduction createCountryOfProduction(com.filmoteka.sdo.CountryOfProduction countryOfProduction) {
        CountryOfProduction countryOfProduction1 = new CountryOfProduction();
        countryOfProduction1.setName(countryOfProduction.getName());
        countryOfProduction1 = countryOfProductionRepository.save(countryOfProduction1);
        com.filmoteka.sdo.CountryOfProduction saved = new com.filmoteka.sdo.CountryOfProduction(countryOfProduction1);
        return saved;
    }


    public com.filmoteka.sdo.CountryOfProduction updateCountryOfProduction(com.filmoteka.sdo.CountryOfProduction countryOfProduction, Long id) throws IncorrectIdException {

        CountryOfProduction countryOfProduction1 = new CountryOfProduction();
        if (!countryOfProduction.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        countryOfProduction1 = countryOfProductionRepository.getOne(id);
        countryOfProduction1.setName(countryOfProduction.getName());
        countryOfProduction1 = countryOfProductionRepository.save(countryOfProduction1);
        com.filmoteka.sdo.CountryOfProduction saved = new com.filmoteka.sdo.CountryOfProduction(countryOfProduction1);
        return saved;
    }

    public List<com.filmoteka.sdo.CountryOfProduction> getAllCountryOfProductions() {
        List<com.filmoteka.sdo.CountryOfProduction> countryOfProductionList = new ArrayList<com.filmoteka.sdo.CountryOfProduction>();
        for (CountryOfProduction countryOfProductionDao : countryOfProductionRepository.findAll()) {
            com.filmoteka.sdo.CountryOfProduction countryOfProduction = new com.filmoteka.sdo.CountryOfProduction(countryOfProductionDao);
            countryOfProductionList.add(countryOfProduction);
        }
        return countryOfProductionList;
    }

}
