package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.util.Messages;
import se.ju23.typespeeder.util.UserInputService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Menu implements MenuService {

    private final UserInputService userInputService;
    private String language = "sv";

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public Menu(UserInputService userInputService) {
        this.userInputService = userInputService;
    }

    public void loginMenu(){
        System.out.print("""
            Please choose an option below:
            1 - Login
            2 - Create new player
            3 - Exit Program
            >\s""");
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


    public void languageChoice() {
        String selectedLanguage = "";
        System.out.println("Välj språk (svenska/engelska):");
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
                    System.out.println("Invalid language selection. Please try again.");
            }
        }
    }

    public void displayMenu() {
        if(language == null){
            languageChoice();
        }
        List<String> menuOptions = getMenuOptions();
        System.out.println("Menu Options - " +
                (language.equals("sv") ? "Svenska" : "English") + ": ");
        menuOptions.forEach(System.out::println);
    }
}
