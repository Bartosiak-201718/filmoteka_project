package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.Actor;
import com.filmoteka.dao.Award;
import com.filmoteka.repository.ActorRepository;
import com.filmoteka.repository.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class AwardService {

    final Logger logger = LogManager.getLogger(AwardService.class);

    @Autowired
    AwardRepository awardRepository;

    public Award getById(Long id) {
        return awardRepository.getOne(id);
    }

    public void deleteById(Long id) {
        try {
            awardRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.Award createAward(com.filmoteka.sdo.Award award) {
        Award a = new Award();
        a.setName(award.getName());
        a = awardRepository.save(a);
        com.filmoteka.sdo.Award saved = new com.filmoteka.sdo.Award(a);
        return saved;
    }


    public com.filmoteka.sdo.Award updateAward(com.filmoteka.sdo.Award award, Long id) throws IncorrectIdException {

        Award award1 = new Award();
        if (!award.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        award1 = awardRepository.getOne(id);
        award1.setName(award.getName());
        award1 = awardRepository.save(award1);
        com.filmoteka.sdo.Award saved = new com.filmoteka.sdo.Award(award1);
        return saved;
    }

    public List<com.filmoteka.sdo.Award> getAllAwards() {
        List<com.filmoteka.sdo.Award> awardList = new ArrayList<com.filmoteka.sdo.Award>();
        for (Award awardDao : awardRepository.findAll()) {
            com.filmoteka.sdo.Award award = new com.filmoteka.sdo.Award(awardDao);
            awardList.add(award);
        }
        return awardList;
    }


}
