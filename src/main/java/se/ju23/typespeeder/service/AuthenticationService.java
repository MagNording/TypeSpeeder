package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repositories.PlayerRepo;
import se.ju23.typespeeder.ui.HandleMenuOptions;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInputService;

@Service
public class AuthenticationService {

    private final Menu menu;
    private final PlayerService playerService;
    private final PlayerRepo playerRepo;
    private final UserInputService userInputService;
    private final HandleMenuOptions handleMenuOptions;

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Autowired
    public AuthenticationService(Menu menu, PlayerService playerService, PlayerRepo playerRepo,
                                 UserInputService userInputService, HandleMenuOptions handleMenuOptions) {
        this.menu = menu;
        this.playerService = playerService;
        this.playerRepo = playerRepo;
        this.userInputService = userInputService;
        this.handleMenuOptions = handleMenuOptions;
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
                setPlayer(playerFound);
                System.out.println("Welcome, " + playerFound.getPlayername());
                handleMenuOptions.mainMenu();
                break;
            }
        }
    }


}
