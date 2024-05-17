package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EditUserMenu implements MenuService{

    private final Menu menu;

    public EditUserMenu(Menu menu) {
        this.menu = menu;
    }

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
        options.add("1. Ändra användarnamn");
        options.add("2. Ändra lösenord");
        options.add("3. Ändra visningsnamn");
        options.add("4. Åter till huvudmeny");
        return options;
    }

    public List<String> getMenuOptionsEnglish() {
        List<String> options = new ArrayList<>();
        options.add("1. Edit username");
        options.add("2. Edit password");
        options.add("3. Edit playername");
        options.add("4. Back to main menu");
        return options;
    }
}
