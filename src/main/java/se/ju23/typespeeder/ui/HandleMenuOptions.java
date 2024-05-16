package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.NewsLetter;
import se.ju23.typespeeder.Patch;
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

    public void mainMenu() {
        int option;
        do {
            menu.displayMenu();
            option = userInputService.getIntInput();

            Player player = authenticationService.getPlayer();

            switch (option) {
                case 1 -> challengeMenu(player);
                case 2 -> { userInputService.outtro();
                    System.exit(0);}
                case 3 -> editUserMenu(player);
                case 4 -> gameService.printTopResults();
                case 5 -> System.out.println(newsLetter.getContent());
                case 6 -> {
                    String language = menu.getLanguage();
                    if (language.equals("svenska")) {
                        System.out.println("Switching to English...");
                        menu.setLanguage("engelska");
                    } else {
                        System.out.println("Byter till svenska...");
                        menu.setLanguage("svenska");
                    }
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        } while (option != 2);
    }

    public void challengeMenu(Player player) {
        int option = 0;
        do {
            challengeMenu.displayMenu();
            option = userInputService.getIntInput();
            switch (option) {
                case 1 -> challenge.startChallenge(player);
                case 2 -> System.out.println(player.toString());
                case 3 -> gameService.printTopResults();
                case 4 -> System.out.println("Exiting challenge menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
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
                case 4 -> System.out.println("Exiting edit user menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
    }

    public void showVersion(){
        Patch patch = new Patch();
        patch.setPatchVersion("1.5.2");
        System.out.println("Version: " + patch.getPatchVersion());
    }

}
