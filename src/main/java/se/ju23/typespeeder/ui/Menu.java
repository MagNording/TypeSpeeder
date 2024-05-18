package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.util.Messages;
import se.ju23.typespeeder.util.UserInputService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Menu implements MenuService {

    private final UserInputService userInputService;
    private String language;

    public Menu(UserInputService userInputService) {
        this.userInputService = userInputService;
        promptLanguageChoice();
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void loginMenu() {
        Messages messages = new Messages(language);
        System.out.println(messages.get("login.menu.prompt"));
        System.out.println(messages.get("login.menu.option1"));
        System.out.println(messages.get("login.menu.option2"));
        System.out.println(messages.get("login.menu.option3"));
        System.out.print(">\s");
    }

    @Override
    public List<String> getMenuOptions() {
        Messages messages = new Messages(language);
        List<String> options = new ArrayList<>();
        options.add(messages.get("menu.option.play"));
        options.add(messages.get("menu.option.exit"));
        options.add(messages.get("menu.option.updatePlayer"));
        options.add(messages.get("menu.option.resultList"));
        options.add(messages.get("menu.option.newsLetter"));
        options.add(messages.get("menu.option.switchLanguage"));
        return options;
    }

    public void displayMenu() {
        List<String> menuOptions = getMenuOptions();
        System.out.println("Menu Options - " +
                (language.equals("sv") ? "svenska" : "english") + ": ");
        menuOptions.forEach(System.out::println);
    }

    public void promptLanguageChoice() {
        String selectedLanguage = "";
        System.out.print("Välj språk (svenska/engelska): \n> ");
        while (!selectedLanguage.equals("svenska") && !selectedLanguage.equals("engelska")) {
            selectedLanguage = userInputService.nextLine().toLowerCase().trim();

            switch (selectedLanguage) {
                case "svenska", "s" -> {
                    System.out.println("Svenska valt.");
                    language = "sv";}
                case "engelska", "e" -> {
                    System.out.println("English chosen.");
                    language = "en"; }
                default ->
                        System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
