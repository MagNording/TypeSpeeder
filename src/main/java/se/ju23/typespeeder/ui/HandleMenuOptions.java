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
                case 1 -> challenge.startChallenge(player);
                case 2 -> {
                    System.out.println("Avsluta spel...");
                    return false;
                }
                case 3 -> editUserMenu();
                case 4 -> System.out.println("Mina resultat...");
                case 5 -> System.out.println("Resultatlista...");
                case 6 -> {
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

    public boolean challangeMenu() {
        int option = 0;
        do {

            challangeMenu.displayMenu();

            option = userInputService.getIntInput();

            switch (option) {
                case 1 -> System.out.println("standardspel");
                case 2 -> System.out.println("asdasdsdsdsd...");
                case 3 -> System.out.println("asdcken...");
                case 4 -> System.out.println("Misdesultat...");
                case 5 -> System.out.println("Resdtlista...");
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    return true;
                }
            }
            return true;
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
