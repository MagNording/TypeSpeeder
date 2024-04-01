package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.entity.PlayerRepo;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInputService;


@Service
public class AuthenticationService {

    private final Menu menu;
    private final PlayerService playerService;
    private final PlayerRepo playerRepo;
    private final UserInputService userInputService;

    @Autowired
    public AuthenticationService(Menu menu, PlayerService playerService, PlayerRepo playerRepo, UserInputService userInputService) {
        this.menu = menu;
        this.playerService = playerService;
        this.playerRepo = playerRepo;
        this.userInputService = userInputService;
    }

    public void login() {
        boolean runProgram = true;
        do {
            System.out.println("Enter your username: ");
            String userNameInput = userInputService.readString();

            System.out.println("Enter password: ");
            String passwordInput = userInputService.readString();

            Player playerFound = playerRepo.findByUsernameAndPassword(userNameInput, passwordInput);

            if (playerFound == null) {
                System.out.println("Player not found.");
                runProgram = false;
            } else {
                System.out.println("Welcome, " + playerFound.getPlayername());
                System.out.println("Your current role is " + playerFound.getRole());
                menu.displayMenu();
                runProgram = false;
            }
        } while (runProgram);
    }
}
