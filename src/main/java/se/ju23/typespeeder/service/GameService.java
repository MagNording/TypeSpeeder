package se.ju23.typespeeder.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Result;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.Messages;

import java.util.List;

@Service
public class GameService {

    @PersistenceContext
    private EntityManager entityManager;
    private final Menu menu;
    private Messages messages;

    @Autowired
    public GameService(Menu menu) {
        this.menu = menu;
        this.messages = new Messages(menu.getLanguage());

    }

    // Additional constructor to accept a temporary EntityManager
    public GameService(EntityManager entityManager, Menu menu) {
        this.entityManager = entityManager;
        this.menu = menu;
        this.messages = new Messages(menu.getLanguage());
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

        System.out.println(messages.get("results.fastest"));
        System.out.println(formatResults(topResultsByTime));

        System.out.println(messages.get("results.mostAccurate"));
        System.out.println(formatResults(topResultsByAccuracy));
    }

    private String formatResults(List<Result> results) {
        StringBuilder sb = new StringBuilder();
        for (Result result : results) {
            sb.append(result.toLocalizedString(messages)).append("\n");
        }
        return sb.toString();
    }

    public void updateMessages() {
        this.messages = new Messages(menu.getLanguage());
    }
}
