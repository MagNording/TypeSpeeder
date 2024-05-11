package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.service.AuthenticationService;
import se.ju23.typespeeder.service.PlayerService;
import se.ju23.typespeeder.util.UserInputService;

@Component
public class Runner implements CommandLineRunner {

    private final AuthenticationService authenticationService;
    private final PlayerService playerService;
    private final UserInputService userInputService;

    @Autowired
    public Runner(AuthenticationService authenticationService, PlayerService playerService,
                  UserInputService userInputService) {
        this.authenticationService = authenticationService;
        this.playerService = playerService;
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

            int userChoice = userInputService.getIntInput();
            switch (userChoice) {
                case 1 -> authenticationService.login();
                case 2 -> createNewPlayer();
                case 3 -> {
                    System.out.println("Thank you, welcome back!");
                    exitProgram = true;
                }
            }
        } while (!exitProgram);
    }

    public void createNewPlayer() {
        System.out.println("Enter player name: ");
        String playername = userInputService.nextLine();

        System.out.println("Enter username: ");
        String username = userInputService.nextLine();

        System.out.println("Enter password: ");
        String password = userInputService.nextLine();

        playerService.addNewPlayer(playername, username, password);
        System.out.println("New player created successfully.");
    }
}
