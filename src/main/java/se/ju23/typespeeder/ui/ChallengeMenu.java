package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.util.UserInputService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChallengeMenu implements MenuService {

    @Autowired
    Menu menu;

    @Autowired
    UserInputService inputService;

    // Constructor Injection no?
//    public ChallengeMenu(Menu menu, UserInputService inputService) {
//        this.menu = menu;
//        this.inputService = inputService;
//    }

    @Override
    public void displayMenu() {

        List<String> menuOptions;

        if (menu.getLanguage().equals("svenska")) {
            menuOptions = getMenuOptions();
            for (String option : menuOptions) {
                System.out.println(option);
            }
        } else {
            menuOptions = getMenuOptionsEnglish();
            for (String option : menuOptions) {
                System.out.println(option);
            }
        }
    }

    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("1. Starta nytt spel");
        options.add("2. Mina resultat");
        options.add("3. Resultatlista");
        options.add("4. Åter till huvudmeny");
        return options;
    }

    public List<String> getMenuOptionsEnglish() {
        List<String> options = new ArrayList<>();
        options.add("1. Start new game");
        options.add("2. My results");
        options.add("3. Leaderboard");
        options.add("4. Back to main menu");
        return options;
    }
}
