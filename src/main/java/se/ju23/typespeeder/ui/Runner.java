package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.service.AuthenticationService;
import se.ju23.typespeeder.service.PlayerService;
import se.ju23.typespeeder.util.UserInputService;

@Component
public class Runner implements CommandLineRunner {

    private final AuthenticationService authenticationService;
    private final PlayerService playerService;
    private final Menu menu;
    private final UserInputService userInputService;

    @Autowired
    public Runner(AuthenticationService authenticationService, PlayerService playerService,
                  Menu menu, UserInputService userInputService) {
        this.authenticationService = authenticationService;
        this.playerService = playerService;
        this.menu = menu;
        this.userInputService = userInputService;
    }

    @Override
    public void run(String... args) {
        boolean exitProgram = false;

        do {
            System.out.println("""
                Please choose an option below:
                1 - Login
                2 - Create new player
                3 - Exit Program""");

            int userChoice = userInputService.readInt();
            switch (userChoice) {
                case 1:
                    authenticationService.login();
                    break;
                case 2:
                    createNewPlayer();
                    break;
                case 3:
                    System.out.println("Thank you, welcome back!");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        } while (!exitProgram);
    }

    private void createNewPlayer() {
        System.out.println("Enter the player name:");
        String playername = userInputService.readString();

        System.out.println("Enter username:");
        String username = userInputService.readString();

        System.out.println("Enter password:");
        String password = userInputService.readString();

        playerService.addNewPlayer(playername, username, password);
        System.out.println("New player created successfully.");
    }
}
