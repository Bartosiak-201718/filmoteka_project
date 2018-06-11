package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.Distributor;
import com.filmoteka.repository.DistributorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributorService {

    final Logger logger = LogManager.getLogger(DistributorService.class);

    @Autowired
    DistributorRepository distributorRepository;

    public Distributor getById(Long id) {
        return distributorRepository.getOne(id);
    }


    public void deleteById(Long id) {
        try {
            distributorRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.Distributor createDistributor(com.filmoteka.sdo.Distributor distributor) {
        Distributor d = new Distributor();
        d.setName(distributor.getName());

        d = distributorRepository.save(d);
        com.filmoteka.sdo.Distributor saved = new com.filmoteka.sdo.Distributor(d);
        return saved;
    }


    public com.filmoteka.sdo.Distributor updateDistributor(com.filmoteka.sdo.Distributor distributor, Long id) throws IncorrectIdException {

        Distributor distributor1 = new Distributor();
        if (!distributor.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        distributor1 = distributorRepository.getOne(id);
        distributor1.setName(distributor.getName());
        distributor1 = distributorRepository.save(distributor1);
        com.filmoteka.sdo.Distributor saved = new com.filmoteka.sdo.Distributor(distributor1);
        return saved;
    }

    public List<com.filmoteka.sdo.Distributor> getAllDistributors() {
        List<com.filmoteka.sdo.Distributor> distributorList = new ArrayList<>();
        for (Distributor distributorDao : distributorRepository.findAll()) {
            com.filmoteka.sdo.Distributor distributor = new com.filmoteka.sdo.Distributor(distributorDao);
            distributorList.add(distributor);
        }
        return distributorList;
    }
}
