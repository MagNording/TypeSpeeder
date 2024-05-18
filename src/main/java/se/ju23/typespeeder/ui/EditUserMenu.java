package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.util.Messages;

import java.util.ArrayList;
import java.util.List;

@Component
public class EditUserMenu implements MenuService{

    private final Menu menu;
    private Messages messages;

    public EditUserMenu(Menu menu) {
        this.menu = menu;
        this.messages = new Messages(menu.getLanguage());
    }

    private void updateMessages() {
        this.messages = new Messages(menu.getLanguage());
    }

    @Override
    public void displayMenu() {
        updateMessages();
        List<String> menuOptions = getMenuOptions();
        for (String option : menuOptions) {
            System.out.println(option);
        }
    }

    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add(messages.get("update.menu.username"));
        options.add(messages.get("update.menu.password"));
        options.add(messages.get("update.menu.playername"));
        options.add(messages.get("update.menu.exit"));
        return options;
    }
}
