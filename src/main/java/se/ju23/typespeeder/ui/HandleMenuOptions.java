package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.NewsLetter;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.logic.Challenge;
import se.ju23.typespeeder.service.AuthenticationService;
import se.ju23.typespeeder.service.GameService;
import se.ju23.typespeeder.service.PlayerService;
import se.ju23.typespeeder.util.UserInputService;

@Component
public class HandleMenuOptions {

    @Autowired
    Menu menu;

    @Autowired
    ChallengeMenu challengeMenu;

    @Autowired
    UserInputService userInputService;

    @Autowired
    Challenge challenge;

    @Autowired
    @Lazy // Lazy loading för att undvika cirkulärt beroende
    AuthenticationService authenticationService;

    @Autowired
    NewsLetter newsLetter;

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @Autowired
    EditUserMenu editUserMenu;

    public boolean mainMenu() {
        int option;
        do {
            menu.displayMenu();

            option = userInputService.getIntInput();

            Player player = authenticationService.getPlayer();

            switch (option) {
                case 1 -> challengeMenu(player);
                case 2 -> {
                    System.out.println("Avsluta spel...");
                    return false;
                }
                case 3 -> editUserMenu(player);
                case 4 -> gameService.printTopResults();
                case 5 -> System.out.println(newsLetter.getContent());
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

    public void challengeMenu(Player player) {
        int option = 0;
        do {
            challengeMenu.displayMenu();
            option = userInputService.getIntInput();
            switch (option) {
                case 1 -> challenge.startChallenge(player);
                case 2 -> System.out.println("Användarens resultat...");
                case 3 -> gameService.printTopResults(); // findall på resultat
                case 4 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    return;
                }
            }
            return;
        } while (true);
    }

    public void editUserMenu(Player player) {
        int option = 0;

        do {
            editUserMenu.displayMenu();
            option = userInputService.getIntInput();
            switch (option) {
                case 1 -> playerService.editUserName(player);
                case 2 -> playerService.editPassword(player);
                case 3 -> playerService.editPlayerName(player);
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    return;
                }
            }
            return;
        } while (true);
    }

}
