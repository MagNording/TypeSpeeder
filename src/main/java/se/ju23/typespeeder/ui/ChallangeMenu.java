package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.util.UserInputService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChallangeMenu implements MenuService {

    @Autowired
    Menu menu;

    @Autowired
    UserInputService inputService;

//    public ChallangeMenu(Menu menu, UserInputService inputService) {
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
        options.add("1. Starta nytt standard spel");
        options.add("2. Starta nytt svårt spel");
        options.add("3. Enskilda tecken");
        options.add("4. Mina resultat");
        options.add("5. Resultatlista");
        options.add("6. Åter till huvudmeny");
        return options;
    }

    public List<String> getMenuOptionsEnglish() {
        List<String> options = new ArrayList<>();
        options.add("1. Start new standard game");
        options.add("2. Start new hard game");
        options.add("3. Start separate characters game");
        options.add("4. My results");
        options.add("5. Leaderboard");
        options.add("6. Back to main menu");
        return options;
    }


}
