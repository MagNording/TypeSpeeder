package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.util.UserInputService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Menu implements MenuService {

    private final UserInputService userInputService;

    private String language = "svenska";

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
        List<String> options = new ArrayList<>();
        options.add("1. Spela spel");
        options.add("2. Avsluta");
        options.add("3. Uppdatera spelare");
        options.add("4. Resultatlista");
        options.add("5. Nyhetsbrev");
        options.add("6. Byt till engelska");
        return options;
    }

    public List<String> getMenuOptionsEnglish() {
        List<String> options = new ArrayList<>();
        options.add("1. Play game");
        options.add("2. Exit program");
        options.add("3. Update player");
        options.add("4. Result list");
        options.add("5. NewsLetter");
        options.add("6. Switch to swedish");
        return options;
    }

    public void languageChoice() {
        String selectedLanguage = "";
        System.out.println("Välj språk (svenska/engelska):");
        while (!selectedLanguage.equals("svenska") && !selectedLanguage.equals("engelska")) {
            selectedLanguage = userInputService.nextLine().toLowerCase().trim();

            switch (selectedLanguage) {
                case "svenska", "s":
                    System.out.println("Svenska valt.");
                    language = "svenska";
                    break;
                case "engelska", "e":
                    System.out.println("Engelska valt.");
                    language = "engelska";
                    break;
                default:
                    System.out.println("Invalid language selection. Please try again.");
            }
        }
    }

    public void displayMenu() {

        if(language == null){
            languageChoice();
        }

        List<String> menuOptions = language.equalsIgnoreCase("svenska") ? getMenuOptions() : getMenuOptionsEnglish();
        System.out.println("Menu Options - " + language + ": ");
        menuOptions.forEach(System.out::println);

    }
}
