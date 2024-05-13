package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.logic.Challenge;
import se.ju23.typespeeder.service.AuthenticationService;
import se.ju23.typespeeder.util.UserInputService;

@Component
public class HandleMenuOptions {

    @Autowired
    Menu menu;

    @Autowired
    ChallangeMenu challangeMenu;

    @Autowired
    UserInputService userInputService;

    @Autowired
    Challenge challenge;

    @Autowired
    @Lazy
    AuthenticationService authenticationService;

    public boolean mainMenu() {
        int option;
        do {
            menu.displayMenu();

            option = userInputService.getIntInput();

            Player player = authenticationService.getPlayer();

            switch (option) {
                case 1 -> challangeMenu(player);
                case 2 -> {
                    System.out.println("Avsluta spel...");
                    return false;
                }
                case 3 -> editUserMenu();
                case 4 -> System.out.println("Mina resultat...");
                case 5 -> System.out.println("Resultatlista...");
                case 6 -> System.out.println("newsletter...");
                case 7 -> {
                    String language = menu.getLanguage();
                    if (language.equals("svenska")) {
                        System.out.println("Switching to English...");
                        language = "engelska";
                    } else {
                        System.out.println("Byter till svenska...");
                        language = "svenska";
                    }
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    return true;
                }
            }
            return true;

        } while (option != 2);
    }

    public void challangeMenu(Player player) {
        int option = 0;
        do {

            challangeMenu.displayMenu();

            option = userInputService.getIntInput();

            switch (option) {
                case 1 -> challenge.startChallenge(player);
                case 2 -> System.out.println("Användarens resultat...");
                case 3 -> System.out.println("global resultatslista..."); // findall på resultsrepo
                case 4 -> {return;}
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    return;
                }
            }
            return;
        } while (true);
    }

    public boolean editUserMenu() {
        int option = 0;
        do {

//            editUserMenu().displayMenu();

            option = userInputService.getIntInput();

            switch (option) {
                case 1 -> System.out.println("Ändra användarnamn..."); //metoder i playerservice
                case 2 -> System.out.println("uppdatera lösenord...");//metoder i playerservice
                case 3 -> System.out.println("ändra playername");//metoder i playerservice
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    return true;
                }
            }
            return true;
        } while (true);
    }


}
