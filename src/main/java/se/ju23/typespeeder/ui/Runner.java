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
    private final Menu menu;

    @Autowired
    public Runner(AuthenticationService authenticationService, PlayerService playerService,
                  UserInputService userInputService, Menu menu) {
        this.authenticationService = authenticationService;
        this.playerService = playerService;
        this.userInputService = userInputService;
        this.menu = menu;
    }

    @Override
    public void run(String... args) {
        boolean exitProgram = false;

        userInputService.intro();

        do {
            menu.loginMenu();

            int userChoice = userInputService.getIntInput();
            switch (userChoice) {
                case 1 -> authenticationService.login();
                case 2 -> playerService.createNewPlayer();
                case 3 -> { userInputService.outtro();
                    exitProgram = true; }
                default -> userInputService.invalidChoice();}
        } while (!exitProgram);
    }
}
