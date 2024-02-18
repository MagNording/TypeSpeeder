package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.entity.PlayerRepo;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInput;

@Service
public class AuthenticationService {

    private final Menu menu;
    private final PlayerService playerService;
    private final PlayerRepo playerRepo; // Assuming PlayerRepo is an interface that extends, for example, JpaRepository

    @Autowired
    public AuthenticationService(Menu menu, PlayerService playerService, PlayerRepo playerRepo) {
        this.menu = menu;
        this.playerService = playerService;
        this.playerRepo = playerRepo; // Injecting PlayerRepo
    }

    public void login() {
        boolean runProgram = true;
        do {
            System.out.println("Enter your username: ");
            String userNameInput = UserInput.readString();

            System.out.println("Enter corresponding password: ");
            String passwordInput = UserInput.readString();

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
