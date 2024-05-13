package se.ju23.typespeeder.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Result;
import se.ju23.typespeeder.ui.Menu;

import java.util.List;

@Service
public class GameService {

    private final EntityManager entityManager;
    private final Menu menu;

    @Autowired
    public GameService(EntityManager entityManager, Menu menu) {
        this.entityManager = entityManager;
        this.menu = menu;
    }

    public List<Result> getTopResultsByTime() {
        // Hämta de snabbaste resultaten från databasen
        return entityManager.createQuery("SELECT r FROM Result r ORDER BY r.timeResult ASC", Result.class)
                .setMaxResults(20)
                .getResultList();
    }

    public List<Result> getTopResultsByAccuracy() {
        // Hämta de mest korrekta resultaten från databasen
        return entityManager.createQuery("SELECT r FROM Result r ORDER BY r.amountResult DESC", Result.class)
                .setMaxResults(20)
                .getResultList();
    }

    public void printTopResults(){
        if (menu.getLanguage().equals("svenska")){
            System.out.println(" Snabbaste spelarna: "+ getTopResultsByTime()
                    + "\nMest korrekta spelarna: " + getTopResultsByAccuracy());
        }else {
            System.out.println(" Fastest players: "+ getTopResultsByTime()
                    + "\nMost accurate players: " + getTopResultsByAccuracy());
        }
    }

}
