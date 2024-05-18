package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.util.Messages;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChallengeMenu implements MenuService {

    private final Menu menu;

    public ChallengeMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void displayMenu() {
        List<String> menuOptions = getMenuOptions();
        for (String option : menuOptions) {
            System.out.println(option);
        }
    }

    @Override
    public List<String> getMenuOptions() {
        Messages messages = new Messages(menu.getLanguage());
        List<String> options = new ArrayList<>();
        options.add(messages.get("challenge.menu.option.startNewGame"));
        options.add(messages.get("challenge.menu.option.myResults"));
        options.add(messages.get("challenge.menu.option.leaderboard"));
        options.add(messages.get("challenge.menu.option.backToMainMenu"));
        return options;
    }
}
