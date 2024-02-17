package se.ju23.typespeeder.ui;

import se.ju23.typespeeder.util.UserInput;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu implements MenuService {
    Scanner input = new Scanner(System.in);
    private String language = "svenska";

    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();

        options.add("1. Spela spel");
        options.add("2. Avsluta spel");
        options.add("3. Lägg till spelare");
        options.add("4. Mina resultat");
        options.add("5. Resultatlista");
        options.add("6. Byt till engelska");
        return options;
    }

    public List<String> getMenuOptionsEnglish() {
        List<String> options = new ArrayList<>();

        options.add("1. Play game");
        options.add("2. Finish game");
        options.add("3. Add player");
        options.add("4. My results");
        options.add("5. Result list");
        options.add("6. Switch to swedish");
        return options;
    }

    @Override
    public void displayMenu() {
        languageChoice();
        List<String> menuOptions;
        if (language.equalsIgnoreCase("svenska")) {
            menuOptions = getMenuOptions();
            System.out.println("Meny Alternativ - " + language + ": ");
        } else {
            menuOptions = getMenuOptionsEnglish();
            System.out.println("Menu Alternativ - " + language + ": ");
        }
        for (String option : menuOptions) {
            System.out.println(option);
        }
    }

    public void languageChoice() {
        try {
            System.out.println("Välj språk (svenska/engelska):");
            String selectedLanguage = "svenska";

            while (selectedLanguage.isBlank()) {
                if (input.hasNextLine()) {
                    selectedLanguage = UserInput.readString().toLowerCase();
                } else {
                    System.out.println("No input detected. Please enter a language choice.");
                }
            }
            if (selectedLanguage.equals("svenska") || selectedLanguage.equals("s")) {
                System.out.println("Svenska valt.");
                language = "svenska";
            } else if (selectedLanguage.equals("engelska") || selectedLanguage.equals("e")) {
                language = "engelska";
            } else {
                System.out.println("Invalid language selection. Default language set to English.");
                language = "English";
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e);
        }
    }
}
