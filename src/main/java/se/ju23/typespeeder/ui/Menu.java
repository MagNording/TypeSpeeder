package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.util.UserInputService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Menu implements MenuService {
    private final UserInputService inputService;
    private String language;

    public Menu(UserInputService inputService) {
        this.inputService = inputService;
        language = "svenska";
    }


    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("1. Spela spel");
        options.add("2. Avsluta spel");
        options.add("3. Uppdatera spelare");
        options.add("4. Mina resultat");
        options.add("5. Resultatlista");
        options.add("6. Byt till engelska");
        return options;
    }

    public boolean handleMenuOption(int option) {
        switch (option) {
            case 1 -> System.out.println("Spela spel...");
            case 2 -> {
                System.out.println("Avsluta spel...");
                return false;}
            case 3 -> System.out.println("Uppdatera dina uppgifter...");
            case 4 -> System.out.println("Mina resultat...");
            case 5 -> System.out.println("Resultatlista...");
            case 6 -> {
                if (language.equals("svenska")) {
                    System.out.println("Switching to English...");
                    language = "engelska";
                } else {
                    System.out.println("Byter till svenska...");
                    language = "svenska";
                }}
            default -> {
                System.out.println("Invalid option. Please try again.");
                return true; }
        }
        return true;
    }


    public List<String> getMenuOptionsEnglish() {
        List<String> options = new ArrayList<>();
        options.add("1. Play game");
        options.add("2. Finish game");
        options.add("3. Update player");
        options.add("4. My results");
        options.add("5. Result list");
        options.add("6. Switch to swedish");
        return options;
    }

    public void displayMenu() {
        if (language == null) {
            languageChoice();
        }
        boolean continueMenu = true;
        while (continueMenu) {
            List<String> menuOptions = language.equalsIgnoreCase("svenska") ? getMenuOptions() : getMenuOptionsEnglish();
            System.out.println("Menu Options - " + language + ": ");
            menuOptions.forEach(System.out::println);

            if (!inputService.hasNextInt()) {
                System.out.println("Invalid option. Please enter a number.");
                continue;
            }
            int choice = inputService.getIntInput();
            continueMenu = handleMenuOption(choice);
        }
    }


    public void languageChoice() {
        String selectedLanguage = "";
        System.out.println("Välj språk (svenska/engelska):");
        while (!selectedLanguage.equals("svenska") && !selectedLanguage.equals("engelska")) {
            selectedLanguage = inputService.nextLine().toLowerCase().trim();

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
}
