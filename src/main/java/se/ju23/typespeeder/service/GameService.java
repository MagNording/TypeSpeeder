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
        // Hämta de snabbaste resultaten från databasen med spelarinformation
        return entityManager.createQuery(
                        "SELECT r FROM Result r JOIN FETCH r.player p ORDER BY r.timeResult ASC", Result.class)
                .setMaxResults(10)
                .getResultList();
    }

    public List<Result> getTopResultsByAccuracy() {
        // Hämta de mest korrekta resultaten från databasen med spelarinformation
        return entityManager.createQuery(
                        "SELECT r FROM Result r JOIN FETCH r.player p ORDER BY r.amountResult DESC", Result.class)
                .setMaxResults(10)
                .getResultList();
    }


    public void printTopResults() {
        List<Result> topResultsByTime = getTopResultsByTime();
        List<Result> topResultsByAccuracy = getTopResultsByAccuracy();

        if (menu.getLanguage().equals("svenska")) {
            System.out.println("Snabbaste spelarna: " + formatResults(topResultsByTime));
            System.out.println("Mest korrekta spelarna: " + formatResults(topResultsByAccuracy));
        } else {
            System.out.println("Fastest players: " + formatResults(topResultsByTime));
            System.out.println("Most accurate players: " + formatResults(topResultsByAccuracy));
        }
    }

    private String formatResults(List<Result> results) {
        StringBuilder sb = new StringBuilder();
        for (Result result : results) {
            sb.append(result).append("\n");
        }
        return sb.toString();
    }

}
