package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repositories.PlayerRepo;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInputService;

@Service
public class AuthenticationService {

    private final Menu menu;
    private final PlayerService playerService;
    private final PlayerRepo playerRepo;
    private final UserInputService userInputService;

    @Autowired
    public AuthenticationService(Menu menu, PlayerService playerService, PlayerRepo playerRepo,
                                 UserInputService userInputService) {
        this.menu = menu;
        this.playerService = playerService;
        this.playerRepo = playerRepo;
        this.userInputService = userInputService;
    }

    public void login() {
        while (true) {
            System.out.println("Enter your username: ");
            String userNameInput = userInputService.nextLine();

            System.out.println("Enter password: ");
            String passwordInput = userInputService.nextLine();

            Player playerFound = playerRepo.findByUsernameAndPassword(userNameInput, passwordInput);
            if (playerFound == null) {
                System.out.println("Player not found. Try again.");
            } else {
                System.out.println("Welcome, " + playerFound.getPlayername());
                menu.displayMenu();
                break;
            }
        }
    }


}
